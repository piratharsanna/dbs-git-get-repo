package com.dbs.sg.repository;

import com.dbs.sg.model.GitRepoDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GitRepoRepository extends JpaRepository<GitRepoDetails, Long> {
    Optional<GitRepoDetails> findByOwnerNameAndRepoName(String owner, String repoName);
}
