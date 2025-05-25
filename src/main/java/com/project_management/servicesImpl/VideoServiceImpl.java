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
        //List<VideoType> videoTypes = videoTypeRepository.findAllByRoleAndCurrentLevelAndTargetLevel(userBasics.getJobRole(),userBasics.getExperience(),userBasics.getTargetRole());
        VideoResponseDto responseDto = new VideoResponseDto();
        List<VideoResponseDto.VideoCatDto> responseList = new ArrayList<>();


        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        VideoMLRequestDto tempReq = new VideoMLRequestDto();

// Role mapping
        String role = userBasics.getJobRole();
        if ("QA".equalsIgnoreCase(role)) {
            tempReq.setRole("QA Engineer");
        } else if ("PM".equalsIgnoreCase(role)) {
            tempReq.setRole("Product Manager");
        } else {
            tempReq.setRole("Software Engineer");
        }

// Level mapping
        String experience = userBasics.getExperience();
        String target = userBasics.getTargetRole();

        String currentLevel = "";
        String targetLevel = "";

// Map current level based on experience
        if ("Intern".equalsIgnoreCase(experience) || "Associate".equalsIgnoreCase(experience)) {
            currentLevel = "Junior";
        } else if ("Mid".equalsIgnoreCase(experience)) {
            currentLevel = "Mid-level";
        } else if ("Senior".equalsIgnoreCase(experience) || "Lead".equalsIgnoreCase(experience)) {
            currentLevel = "Senior";
        }

// Map target level based on targetRole
        if ("Intern".equalsIgnoreCase(target) || "Associate".equalsIgnoreCase(target)) {
            targetLevel = "Junior";
        } else if ("Mid".equalsIgnoreCase(target)) {
            targetLevel = "Mid-level";
        } else if ("Senior".equalsIgnoreCase(target) || "Lead".equalsIgnoreCase(target)) {
            targetLevel = "Senior";
        }

// Apply override logic
        if (
                ("Intern".equalsIgnoreCase(experience) || "Associate".equalsIgnoreCase(experience)) &&
                        ("Intern".equalsIgnoreCase(target) || "Associate".equalsIgnoreCase(target))
        ) {
            targetLevel = "Mid-level";  // Promote from Junior → Mid if both are low
        } else if (
                ("Associate".equalsIgnoreCase(experience) || "Mid".equalsIgnoreCase(experience)) &&
                        ("Associate".equalsIgnoreCase(target) || "Mid".equalsIgnoreCase(target))
        ) {
            currentLevel = "Junior";  // Demote Associate/Mid to Junior in edge case
        }

        tempReq.setCurrentLevel(currentLevel);
        tempReq.setTargetLevel(targetLevel);




        HttpEntity<VideoMLRequestDto> entity = new HttpEntity<>(tempReq, headers);

            // Send the request to the ML service
            ResponseEntity<VideoMLResponseDto> mlResponse = restTemplate.exchange(
                    videoUrl,
                    HttpMethod.POST,
                    entity,
                    VideoMLResponseDto.class
            );

            VideoMLResponseDto mlResponseDto = mlResponse.getBody();
            List<VideoType> videoTypes = videoTypeRepository.findAllByTopic(mlResponseDto.getPrediction());

        // Promote currentLevel before querying
        if ("Junior".equalsIgnoreCase(currentLevel)) {
            targetLevel = "Mid-level";
        } else if ("Mid-level".equalsIgnoreCase(currentLevel)) {
            targetLevel = "Senior";
        }

//        List<VideoType> videoTypes = videoTypeRepository.findAllByRoleAndCurrentLevelAndTargetLevel(tempReq.getRole(), currentLevel,targetLevel);
            videoTypes.forEach(videoType -> {
                List<Video> videos = videoRepository.findAllByTypeId(videoType.getId());
                VideoResponseDto.VideoCatDto responseDto1 = new VideoResponseDto.VideoCatDto();
                responseDto1.setType(videoType.getType());
                responseDto1.setVideoDtoList(videos);
                responseList.add(responseDto1);
            });


        responseDto.setCatDtoList(responseList);
        return responseDto;
    }
}
