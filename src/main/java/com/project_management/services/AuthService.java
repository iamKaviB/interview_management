package com.project_management.services;

import com.project_management.dto.LoginResponseDTO;
import com.project_management.dto.SignUpDTO;
import com.project_management.dto.UserDTO;
import com.project_management.models.User;

import java.util.List;

public interface AuthService {
    LoginResponseDTO login(UserDTO userDTO);
    User signup(SignUpDTO signUpDTO);
    List<String> getPermissionsByUsername(String username);
}

