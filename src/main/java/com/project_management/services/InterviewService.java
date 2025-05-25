package com.project_management.services;

import com.project_management.dto.*;

public interface InterviewService {
    InterviewResponseDTO getInterview(InterviewRequestDTO requestDTO);
    InterviewResultResponseDTO getResult(InterviewAnswerRequestDto requestDto);
    InterviewAskResponseDTO ask(InterviewAskRequestDTO requestDto);
}
