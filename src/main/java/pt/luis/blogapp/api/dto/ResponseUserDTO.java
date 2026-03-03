package pt.luis.blogapp.api.dto;

import pt.luis.blogapp.api.entities.role.UserRole;

import java.time.LocalDateTime;

public record ResponseUserDTO(
        Long id,
        String username,
        String email,
        UserRole role,
        LocalDateTime createdAt,
        String lastLoginAt
) {}
