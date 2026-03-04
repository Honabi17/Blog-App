package pt.luis.blogapp.api.dto.userDTO;

import pt.luis.blogapp.api.models.role.UserRole;

import java.time.LocalDateTime;

public record ResponseUserDTO(
        Long id,
        String username,
        String email,
        UserRole role,
        LocalDateTime createdAt,
        String lastLoginAt
) {}
