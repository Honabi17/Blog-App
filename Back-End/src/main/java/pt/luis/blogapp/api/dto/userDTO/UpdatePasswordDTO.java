package pt.luis.blogapp.api.dto.userDTO;

import jakarta.validation.constraints.Size;

public record UpdatePasswordDTO(

        @Size(min = 4, message = "Password must have at least 4 characters!")
        String currentPassword,

        @Size(min = 4, message = "Password must have at least 4 characters!")
        String newPassword
) {}