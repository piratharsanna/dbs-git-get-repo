package com.dbs.sg.controller;

import com.dbs.sg.dto.GenericResponse;
import com.dbs.sg.dto.GitRepoDetailsDto;
import com.dbs.sg.service.GitRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class GitRepoController {
    private final GitRepoService gitRepoService;

    @GetMapping("/repositories/{owner}/{repository-name}")
    public ResponseEntity<GenericResponse<GitRepoDetailsDto>> getStoreById(@PathVariable(name = "owner", required = true) String owner, @PathVariable(name = "repository-name", required = true) String repositoryName){
        return gitRepoService.getGitRepo(owner, repositoryName);
    }
}
