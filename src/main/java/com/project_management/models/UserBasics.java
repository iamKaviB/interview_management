package com.project_management.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "user_basics")
@Data
public class UserBasics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String fullName;
    private String jobRole;
    private String experience;
    private String targetRole;
    private String primarySkill;
    private String EnglishLevel;
}
