package com.project_management.servicesImpl;

import com.project_management.dto.CVParseResponseDTO;
import com.project_management.services.CVParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
public class CVParseServiceImpl implements CVParseService {
    @Value("${api.cv.parser.url}")
    private String cvParserUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public CVParseResponseDTO parseCV(File file) {
        // Create request body with file
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<CVParseResponseDTO> response = restTemplate.postForEntity(cvParserUrl, requestEntity, CVParseResponseDTO.class);
        return response.getBody();
    }
}
