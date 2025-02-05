package com.project_management.servicesImpl;

import com.project_management.dto.VideoMLRequestDto;
import com.project_management.dto.VideoMLResponseDto;
import com.project_management.dto.VideoResponseDto;
import com.project_management.models.UserBasics;
import com.project_management.models.Video;
import com.project_management.models.VideoType;
import com.project_management.repositories.VideoRepository;
import com.project_management.repositories.VideoTypeRepository;
import com.project_management.services.UserBasicService;
import com.project_management.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Value("${api.video.url}")
    private String videoUrl;
    @Autowired
    private UserBasicService userBasicService;
    @Autowired
    private VideoTypeRepository videoTypeRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public VideoResponseDto getVideoPredictions(Long userId) {

        UserBasics userBasics = userBasicService.getUserBasicsByUserId(userId);
        List<VideoType> videoTypes = videoTypeRepository.findAllByRoleAndCurrentLevelAndTargetLevel(userBasics.getJobRole(),userBasics.getExperience(),userBasics.getTargetRole());
        VideoResponseDto responseDto = new VideoResponseDto();
        List<VideoResponseDto.VideoCatDto> responseList = new ArrayList<>();


        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        videoTypes.forEach(videoType -> {

            VideoMLRequestDto tempReq = new VideoMLRequestDto();
            tempReq.setRole(userBasics.getJobRole());
            tempReq.setCurrentLevel(userBasics.getExperience());
            tempReq.setTargetLevel(userBasics.getTargetRole());
            tempReq.setSkillTopic(videoType.getTopic());
            tempReq.setChapter("1");

            HttpEntity<VideoMLRequestDto> entity = new HttpEntity<>(tempReq, headers);

            // Send the request to the ML service
            ResponseEntity<VideoMLResponseDto> mlResponse = restTemplate.exchange(
                    videoUrl,
                    HttpMethod.POST,
                    entity,
                    VideoMLResponseDto.class
            );

            VideoMLResponseDto mlResponseDto = mlResponse.getBody();
            assert mlResponseDto != null;
            List<String> videoStringList = Arrays.asList(mlResponseDto.getPrediction().split("\\|"));


            videoStringList.forEach(video->{
                VideoResponseDto.VideoCatDto videoDto = new VideoResponseDto.VideoCatDto();
                videoDto.setType(video);
                videoTypeRepository.findAllByType(video).forEach(temp->{
                   List<Video> videoList =videoRepository.findAllByTypeId(temp.getId());
                   videoDto.setVideoDtoList(videoList);
                });
                responseList.add(videoDto);
            });
        });

        responseDto.setCatDtoList(responseList);
        return responseDto;
    }
}
