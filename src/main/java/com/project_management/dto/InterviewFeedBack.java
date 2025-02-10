package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InterviewFeedBack {
    @JsonProperty("feedback")
    private List<String> feedBacks;
    @JsonProperty("question_id")
    private Long questionId;
    @JsonProperty("score")
    private Double score;
}
