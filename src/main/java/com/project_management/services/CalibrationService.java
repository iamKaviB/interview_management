package com.project_management.services;

import com.project_management.dto.CalibrationAnswerDto;
import com.project_management.dto.CalibrationDto;
import com.project_management.dto.CalibrationTestResultDto;
import com.project_management.models.CalibrationQuestions;

import java.util.List;

public interface CalibrationService {
    CalibrationDto getCalibrationTest(Long userId);
    CalibrationTestResultDto getResultOfTest(CalibrationAnswerDto answerDto);
}
