package com.project_management.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "calibration_history")
public class CalibrationTestResultHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long testId;
    private Double points;
    private Integer correctAnswers;
    private Integer incorrectAnswers;
}
