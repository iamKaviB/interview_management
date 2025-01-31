package com.project_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class CalibrationAnswerDto {

    private Long testId;
    private Long userId;
    private List<CalAnswerDto> answerList;

    @Data
    public static class CalAnswerDto{
        private Long questionId;
        private Long answer;
    }
}
