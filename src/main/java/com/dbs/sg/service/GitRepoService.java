package com.dbs.sg.service;

import com.dbs.sg.dto.GenericResponse;
import com.dbs.sg.dto.GitRepoDetailsDto;
import org.springframework.http.ResponseEntity;

public interface GitRepoService {
    ResponseEntity<GenericResponse<GitRepoDetailsDto>> getGitRepo(String owner,  String repoName);
}
