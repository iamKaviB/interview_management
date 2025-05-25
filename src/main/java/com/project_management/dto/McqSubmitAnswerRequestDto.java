package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class McqSubmitAnswerRequestDto {
    private String action;
    @JsonProperty("response_time")
    private Double responseTime;
}
