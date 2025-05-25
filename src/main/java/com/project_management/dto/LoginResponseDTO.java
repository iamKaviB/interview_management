package com.project_management.dto;

import lombok.Data;
import java.util.List;

@Data
public class LoginResponseDTO {
    private String token;
    private String role;
    private Long userId;
    private List<String> permissions;

    public LoginResponseDTO(String token, String role, Long userId,List<String> permissions) {
        this.token = token;
        this.role = role;
        this.userId = userId;
        this.permissions = permissions;
    }
}
