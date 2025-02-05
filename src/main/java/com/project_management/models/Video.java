package com.project_management.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "video")
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long typeId;
    private String url;
}
