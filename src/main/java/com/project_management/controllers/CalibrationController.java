package com.project_management.controllers;

import com.project_management.dto.CalibrationAnswerDto;
import com.project_management.dto.CalibrationDto;
import com.project_management.dto.CalibrationTestResultDto;
import com.project_management.models.CalibrationQuestions;
import com.project_management.models.UserBasics;
import com.project_management.services.CalibrationService;
import com.project_management.services.UserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calibration")
public class CalibrationController {

    @Autowired
    private CalibrationService calibrationService;


    @GetMapping("/{userId}")
    public ResponseEntity<CalibrationDto> getCalibrationTest(@PathVariable Long userId){
        return ResponseEntity.status(200).body(calibrationService.getCalibrationTest(userId));
    }

    @PostMapping("/result")
    public ResponseEntity<CalibrationTestResultDto> getTestResult(@RequestBody CalibrationAnswerDto dto){
        return ResponseEntity.status(200).body(calibrationService.getResultOfTest(dto));
    }
}
