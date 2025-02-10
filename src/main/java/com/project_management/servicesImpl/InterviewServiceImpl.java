package com.project_management.servicesImpl;

import com.project_management.dto.*;
import com.project_management.services.InterviewService;
import com.project_management.utils.TextToSpeechUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Value("${api.interview.start.url}")
    private String interviewUrl;
    @Value("${api.interview.result.url}")
    private String submitUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TextToSpeechUtil textToSpeechUtil;

    @Override
    public InterviewResponseDTO getInterview(InterviewRequestDTO requestDTO) {

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InterviewRequestDTO> entity = new HttpEntity<>(requestDTO, headers);

        // Send the request to the ML service
        ResponseEntity<InterviewResponseDTO> mlResponse = restTemplate.exchange(
                interviewUrl,
                HttpMethod.POST,
                entity,
                InterviewResponseDTO.class
        );

//        InterviewResponseDTO responseDTO = mlResponse.getBody();
//        responseDTO.getInterviewQuestionsDTOList().forEach(interviewQuestionsDTO -> {
//            String fileName = String.valueOf(interviewQuestionsDTO.getId())+"_"+responseDTO.getInterviewId();
//
//            // Call the service to convert text to speech and save as MP3
//            try {
//                textToSpeechUtil.convertTextToMp3(interviewQuestionsDTO.getQuestion(), fileName);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            // Read the generated MP3 file
//            File mp3File = new File(fileName);
//            responseDTO.getFileList().add(mp3File);
//        });

        return mlResponse.getBody();
    }

    @Override
    public InterviewResultResponseDTO getResult(InterviewAnswerRequestDto requestDto) {
        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InterviewAnswerRequestDto> entity = new HttpEntity<>(requestDto, headers);

        // Send the request to the ML service
        ResponseEntity<InterviewResultResponseDTO> mlResponse = restTemplate.exchange(
                submitUrl,
                HttpMethod.POST,
                entity,
                InterviewResultResponseDTO.class
        );
        return mlResponse.getBody();
    }
}
