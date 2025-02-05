package com.project_management.dto;

import com.project_management.models.Video;
import lombok.Data;

import java.util.List;

@Data
public class VideoResponseDto {

    private List<VideoCatDto> catDtoList;

    @Data
    public static class VideoCatDto{
        private String type;
        private List<Video> videoDtoList;
    }
}
