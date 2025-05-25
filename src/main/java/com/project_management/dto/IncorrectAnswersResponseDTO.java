package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncorrectAnswersResponseDTO{

    @JsonProperty("areas_for_improvement")
    private AreasForImprovementDTO areasForImprovement;

    @JsonProperty("performance_summary")
    private PerformanceSummaryDTO performanceSummary;

    @JsonProperty("recommendations")
    private List<String> recommendations;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("user_data")
    private UserDataDTO userData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AreasForImprovementDTO {
        @JsonProperty("bloom_level_challenges")
        private List<BloomLevelChallengeDTO> bloomLevelChallenges;

        @JsonProperty("challenging_concepts")
        private List<ChallengingConceptDTO> challengingConcepts;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BloomLevelChallengeDTO {
        @JsonProperty("level")
        private String level;

        @JsonProperty("error_count")
        private int errorCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengingConceptDTO {
        @JsonProperty("concept")
        private String concept;

        @JsonProperty("error_count")
        private int errorCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PerformanceSummaryDTO {
        @JsonProperty("accuracy")
        private double accuracy;

        @JsonProperty("correct_answers")
        private int correctAnswers;

        @JsonProperty("progress")
        private List<ProgressDTO> progress;

        @JsonProperty("total_questions")
        private int totalQuestions;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProgressDTO {
        @JsonProperty("question_num")
        private int questionNum;

        @JsonProperty("correct")
        private boolean correct;

        @JsonProperty("response_time")
        private double responseTime;

        @JsonProperty("theta_before")
        private double thetaBefore;

        @JsonProperty("theta_after")
        private double thetaAfter;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDataDTO {
        @JsonProperty("chapter")
        private String chapter;

        @JsonProperty("current_level")
        private String currentLevel;

        @JsonProperty("points")
        private double points;

        @JsonProperty("role")
        private String role;

        @JsonProperty("theta")
        private double theta;
    }
}
