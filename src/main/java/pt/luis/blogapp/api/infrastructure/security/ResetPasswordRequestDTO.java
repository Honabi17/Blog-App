package pt.luis.blogapp.api.infrastructure.security;

import jakarta.validation.constraints.Email;

public record ResetPasswordRequestDTO(String email) {}
