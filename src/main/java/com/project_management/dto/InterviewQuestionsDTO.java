package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InterviewQuestionsDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("category")
    private String category;;
    @JsonProperty("difficulty")
    private Integer difficulty;
    @JsonProperty("question")
    private String question;
    @JsonProperty("ideal_answer")
    private String idealAnswer;
    @JsonProperty("keywords")
    private List<String> keywords;
}
