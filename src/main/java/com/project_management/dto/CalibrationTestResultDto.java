package com.project_management.dto;

import lombok.Data;

@Data
public class CalibrationTestResultDto {
    private Long testId;
    private Integer correctAnswers;
    private Integer wrongAnswers;
    private Double points;
}
