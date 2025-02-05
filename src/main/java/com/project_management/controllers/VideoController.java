package com.project_management.controllers;

import com.project_management.dto.VideoResponseDto;
import com.project_management.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/{userId}")
    public ResponseEntity<VideoResponseDto> getVideoPrediction(@PathVariable Long userId){
        return ResponseEntity.status(200).body(videoService.getVideoPredictions(userId));
    }
}
