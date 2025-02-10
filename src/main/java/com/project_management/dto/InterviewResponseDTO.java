package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class InterviewResponseDTO {
    @JsonProperty("interview_id")
    private Long interviewId;
    @JsonProperty("questions")
    private List<InterviewQuestionsDTO> interviewQuestionsDTOList;
    @JsonIgnore
    private List<File> fileList;
}
