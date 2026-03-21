package pt.luis.blogapp.api.dto.userDTO;

import jakarta.validation.constraints.Email;

public record UpdateEmailDTO(

        @Email(message = "Invalid email format!")
        String currentEmail,

        @Email(message = "Invalid email format!")
        String newEmail
) {}
