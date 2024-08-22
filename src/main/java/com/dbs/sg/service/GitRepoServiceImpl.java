package com.dbs.sg.service;

import com.dbs.sg.ErrorCode;
import com.dbs.sg.builder.GitRepoDetailsBuilder;
import com.dbs.sg.dto.GenericResponse;
import com.dbs.sg.dto.GitRepoDetailsDto;
import com.dbs.sg.exception.CommonException;
import com.dbs.sg.model.GitRepoDetails;
import com.dbs.sg.repository.GitRepoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitRepoServiceImpl implements GitRepoService{

    private final GitRepoRepository gitRepoRepository;
    private final RestTemplate restTemplate;
    private final GitRepoDetailsBuilder gitRepoDetailsBuilder;

    @Value("${dbs.git.repo.baseurl}")
    private String gitRepoBaseUrl;
    @Override
    public ResponseEntity<GenericResponse<GitRepoDetailsDto>> getGitRepo(String owner, String repoName) {
        try {
        Optional<GitRepoDetails> response = gitRepoRepository.findByOwnerNameAndRepoName(owner, repoName);
        if(response.isPresent()){
            GenericResponse<GitRepoDetailsDto> gitRepoDetailsDto = gitRepoDetailsBuilder.entityToGenericResponseDto(response.get());
            return ResponseEntity.status(HttpStatus.OK).body(gitRepoDetailsDto);
        }
        //read from Git repository
        String requestUrl = gitRepoBaseUrl + owner +"/"+repoName;
        String gitResponse = restTemplate.getForObject(requestUrl,String.class, owner,repoName);
        ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(gitResponse);
            JsonNode ownerNode = jsonNode.get("owner");//owner details in nested node
            GitRepoDetails gitRepoDetails = new GitRepoDetails();
            gitRepoDetails.setOwnerName(ownerNode.get("login").asText());
            gitRepoDetails.setRepoName(jsonNode.get("name").asText());
            gitRepoDetails.setDescription(jsonNode.get("description").asText());
            gitRepoDetails.setCloneUrl(jsonNode.get("clone_url").asText());
            gitRepoDetails.setStars(jsonNode.get("stargazers_count").asInt());
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(jsonNode.get("created_at").asText(), DateTimeFormatter.ISO_DATE_TIME);
            gitRepoDetails.setCreatedAt(zonedDateTime.toLocalDateTime());
            GitRepoDetails afterSave = gitRepoRepository.save(gitRepoDetails);
            GenericResponse<GitRepoDetailsDto> gitRepoDetailsDto = gitRepoDetailsBuilder.entityToGenericResponseDto(afterSave);
            return ResponseEntity.status(HttpStatus.OK).body(gitRepoDetailsDto);
        } catch (HttpClientErrorException e){
            throw new CommonException(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }

        catch (Exception e) {
            log.error("Error getGitRepo: "+ e);
            throw new CommonException(ErrorCode.UN_HANDLED.getCode(), ErrorCode.UN_HANDLED.getMessage());
        }
    }
}
