package com.project_management.controllers;

import com.project_management.dto.CVParseResponseDTO;
import com.project_management.services.CVParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/cv")
public class CVController {

    @Autowired
    CVParseService cvParseService;

    @PostMapping("/upload")
    public ResponseEntity<CVParseResponseDTO> uploadCv(@RequestParam("file") MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);
        CVParseResponseDTO result = cvParseService.parseCV(tempFile);
        return ResponseEntity.ok(result);
    }

}
