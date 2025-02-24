package com.project_management.controllers;

import com.project_management.dto.McqRequestDto;
import com.project_management.dto.McqStartResponseDto;
import com.project_management.dto.McqSubmitAnswerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/mcq")
public class McqController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.mcq.start.url}")
    private String mcqStartUrl;

    @Value("${api.mcq.submit.url}")
    private String mcqSubmitUrl;

    @PostMapping
    public ResponseEntity<McqStartResponseDto> startGame(@RequestBody McqRequestDto requestDto){

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<McqRequestDto> entity = new HttpEntity<>(requestDto, headers);

        // Send the request to the ML service
        ResponseEntity<McqStartResponseDto> mlResponse = restTemplate.exchange(
                mcqStartUrl,
                HttpMethod.POST,
                entity,
                McqStartResponseDto.class
        );

        // Check the response status and handle errors
        if (!mlResponse.getStatusCode().is2xxSuccessful() || mlResponse.getBody() == null) {
            throw new RuntimeException("Failed to get prediction from ML service: " +
                    mlResponse.getStatusCode());
        }

        return mlResponse;
    }

    @PostMapping("/submit")
    public ResponseEntity<McqStartResponseDto> submit(@RequestBody McqSubmitAnswerRequestDto requestDto, @RequestHeader(name = "X-Session-ID") String session){

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Session-ID",session);
        HttpEntity<McqSubmitAnswerRequestDto> entity = new HttpEntity<>(requestDto, headers);

        // Send the request to the ML service
        ResponseEntity<McqStartResponseDto> mlResponse = restTemplate.exchange(
                mcqSubmitUrl,
                HttpMethod.POST,
                entity,
                McqStartResponseDto.class
        );

        // Check the response status and handle errors
        if (!mlResponse.getStatusCode().is2xxSuccessful() || mlResponse.getBody() == null) {
            throw new RuntimeException("Failed to get prediction from ML service: " +
                    mlResponse.getStatusCode());
        }

        return mlResponse;
    }



}
