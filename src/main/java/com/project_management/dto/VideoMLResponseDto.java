package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VideoMLResponseDto {
    @JsonProperty("prediction")
    private String prediction;
    @JsonProperty("status")
    private String status;
}
