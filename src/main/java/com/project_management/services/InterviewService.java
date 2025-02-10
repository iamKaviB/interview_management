package com.project_management.services;

import com.project_management.dto.InterviewAnswerRequestDto;
import com.project_management.dto.InterviewRequestDTO;
import com.project_management.dto.InterviewResponseDTO;
import com.project_management.dto.InterviewResultResponseDTO;

public interface InterviewService {
    InterviewResponseDTO getInterview(InterviewRequestDTO requestDTO);
    InterviewResultResponseDTO getResult(InterviewAnswerRequestDto requestDto);
}
