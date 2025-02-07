package com.project_management.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_video_history")
@Data
public class UserVideoHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long videoId;
}
