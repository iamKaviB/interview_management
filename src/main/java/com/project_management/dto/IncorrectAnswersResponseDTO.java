package com.project_management.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncorrectAnswersResponseDTO {
    private List<IncorrectAnswerDTO> incorrectAnswers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IncorrectAnswerDTO {
        private String question;
        private String correctAnswer;
        private String givenAnswer;
        private String sessionId;
    }
}