package pt.luis.blogapp.api.dto;

import pt.luis.blogapp.api.entities.role.UserRole;

public record ResponseUserDTO(
        Long id,
        String username,
        String email,
        UserRole role,
        String lastLoginAt
) {}
