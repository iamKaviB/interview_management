package com.project_management.controllers;

import com.project_management.dto.LoginResponseDTO;
import com.project_management.dto.SignUpDTO;
import com.project_management.dto.UserDTO;
import com.project_management.models.User;
import com.project_management.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDTO> signup(@RequestBody SignUpDTO signUpDTO) {
        User user = authService.signup(signUpDTO);
        UserDTO dto  = new UserDTO();
        dto.setUsername(signUpDTO.getUsername());
        dto.setPassword(signUpDTO.getPassword());
        LoginResponseDTO loginResponse = authService.login(dto);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserDTO userDTO) {
        LoginResponseDTO loginResponse = authService.login(userDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
