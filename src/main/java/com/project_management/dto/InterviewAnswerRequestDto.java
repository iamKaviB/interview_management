package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InterviewAnswerRequestDto {
    @JsonProperty("interview_id")
    private Long interviewId;
    @JsonProperty("answers")
    private List<AnswerRequestDto> asnwerList;


    @Data
    public static class AnswerRequestDto{
        @JsonProperty("question_id")
        private Long questionId;
        @JsonProperty("answer_text")
        private String answer;
    }
}
