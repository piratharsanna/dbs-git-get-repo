package com.dbs.sg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitRepoDetailsDto {
    private Integer id;
    private String repoName;
    private String ownerName;
    private String description;
    private String cloneUrl;
    private Integer stars;
    private LocalDateTime createdAt;
}
