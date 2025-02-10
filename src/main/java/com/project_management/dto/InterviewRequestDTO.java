package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InterviewRequestDTO {
    @JsonProperty("role")
    private String role;
    @JsonProperty("seniority_level")
    private String seniority;
    @JsonProperty("candidate_id")
    private String candidateId;
}
