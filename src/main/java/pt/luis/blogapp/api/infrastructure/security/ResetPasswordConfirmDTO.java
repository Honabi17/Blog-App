package pt.luis.blogapp.api.infrastructure.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordConfirmDTO(

        @NotBlank(message = "Token is required!")
        String token,

        @NotBlank(message = "Password is required")
        @Size(min = 4, message = "Password must have at least 4 characters")
        String newPassword
) {}
