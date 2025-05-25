package com.project_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class McqStartResponseDto {
    private Boolean correct;
    private Float difficulty;
    private Boolean game_over;
    private String level;
    private List<String> options;
    private String question;
    private int reward;
    private String session_id;
    private StateDTO state;
    private Boolean done = false;
    private Integer points = 0;

    @Data
    public static class StateDTO {
        private int consecutive_correct;
        private int consecutive_wrong;
        private String level;
        private float theta;
        private int bloom_level;
    }
}
