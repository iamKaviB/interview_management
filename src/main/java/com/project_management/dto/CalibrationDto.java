package com.project_management.dto;

import com.project_management.models.CalibrationQuestions;
import lombok.Data;

import java.util.List;

@Data
public class CalibrationDto {

    private Long testId;
    private List<CalibrationQuestions> questionsList;
}
