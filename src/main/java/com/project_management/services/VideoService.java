package com.project_management.services;

import com.project_management.dto.VideoResponseDto;

import java.util.List;

public interface VideoService {
    VideoResponseDto getVideoPredictions(Long userId);
}
