package com.dbs.sg.builder;

import com.dbs.sg.dto.GenericResponse;
import com.dbs.sg.dto.GitRepoDetailsDto;
import com.dbs.sg.exception.CommonErrorResponse;
import com.dbs.sg.model.GitRepoDetails;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class GitRepoDetailsBuilder {
    public GitRepoDetailsDto buildGitRepoDetailsToDto(GitRepoDetails gitRepoDetails){
        return GitRepoDetailsDto.builder()
                .id(gitRepoDetails.getId())
                .ownerName(gitRepoDetails.getOwnerName())
                .repoName(gitRepoDetails.getRepoName())
                .cloneUrl(gitRepoDetails.getCloneUrl())
                .stars(gitRepoDetails.getStars())
                .createdAt(gitRepoDetails.getCreatedAt())
                .build();
    }

    public GenericResponse entityToGenericResponseDto(GitRepoDetails response) {
        return GenericResponse.builder()
                .success(true)
                .message("Success")
                .data(buildGitRepoDetailsToDto(response))
                .build();
    }

    public GenericResponse buildExceptionGenericResponse(CommonErrorResponse response) {
        return GenericResponse.builder()
                .success(false)
                .message("Fail")
                .data(response)
                .build();
    }
}
