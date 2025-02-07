package com.project_management.controllers;

import com.project_management.dto.VideoResponseDto;
import com.project_management.models.UserVideoHistory;
import com.project_management.models.Video;
import com.project_management.services.UserVideoHistoryService;
import com.project_management.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private UserVideoHistoryService userVideoHistoryService;

    @GetMapping("/{userId}")
    public ResponseEntity<VideoResponseDto> getVideoPrediction(@PathVariable Long userId){
        return ResponseEntity.status(200).body(videoService.getVideoPredictions(userId));
    }

    @PostMapping("/history/{videoId}/{userId}")
    public ResponseEntity<UserVideoHistory> addHistory(@PathVariable(name = "videoId") Long videoId,@PathVariable(name = "userId") Long userId){
        return ResponseEntity.status(200).body(userVideoHistoryService.addHistory(userId,videoId));
    }

    @GetMapping("/history/{userId}/{offset}")
    public ResponseEntity<List<Video>> getHistory(@PathVariable(name = "offset") Integer offset, @PathVariable(name = "userId") Long userId){
        return ResponseEntity.status(200).body(userVideoHistoryService.getHistoryOfUser(userId,offset));
    }
}
