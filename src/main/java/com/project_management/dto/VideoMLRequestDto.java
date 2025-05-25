package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VideoMLRequestDto {

    @JsonProperty("role")
    private String role;
    @JsonProperty("current_level")
    private String currentLevel;
    @JsonProperty("target_level")
    private String targetLevel;
}
