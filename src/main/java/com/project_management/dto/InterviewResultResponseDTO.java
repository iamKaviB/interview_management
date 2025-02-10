package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InterviewResultResponseDTO {
    @JsonProperty("final_score")
    private Double finalScore;
    @JsonProperty("results")
    private List<InterviewFeedBack> results;
}
