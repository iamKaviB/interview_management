package com.project_management.services;

import com.project_management.dto.CVParseResponseDTO;

import java.io.File;

public interface CVParseService {
    CVParseResponseDTO parseCV(File file);
}
