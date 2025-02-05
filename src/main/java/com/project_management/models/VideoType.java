package com.project_management.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "video_type")
@Data
public class VideoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    private String currentLevel;
    private String targetLevel;
    private String topic;
    private String type;
    private String chapter;
}
