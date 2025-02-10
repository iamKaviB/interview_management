package com.project_management.controllers;

import com.project_management.dto.InterviewAnswerRequestDto;
import com.project_management.dto.InterviewRequestDTO;
import com.project_management.dto.InterviewResponseDTO;
import com.project_management.dto.InterviewResultResponseDTO;
import com.project_management.services.InterviewService;
import com.project_management.utils.TextToSpeechUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/interview")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @Autowired
    private TextToSpeechUtil textToSpeechService;

    @PostMapping
    public ResponseEntity<InterviewResponseDTO> startInterview(@RequestBody InterviewRequestDTO requestDTO){
        return ResponseEntity.status(200).body(interviewService.getInterview(requestDTO));
    }

    @PostMapping("/result")
    public ResponseEntity<InterviewResultResponseDTO> getResult(@RequestBody InterviewAnswerRequestDto requestDto){
        return ResponseEntity.status(200).body(interviewService.getResult(requestDto));
    }

    @PostMapping("/convert")
    public ResponseEntity<byte[]> convertTextToMp3(@RequestBody InterviewRequestDTO requestDTO) {
        try {

            InterviewResponseDTO responseDTO = interviewService.getInterview(requestDTO);
            // Define the output file name
            String fileName = "question_output.mp3";

            // Call the service to convert text to speech and save as MP3
            textToSpeechService.convertTextToMp3("", fileName);

            // Read the generated MP3 file
            File mp3File = new File(fileName);
            byte[] fileContent = Files.readAllBytes(mp3File.toPath());

            // Set the response headers for MP3 file
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");

            // Return the MP3 file as the response body
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
