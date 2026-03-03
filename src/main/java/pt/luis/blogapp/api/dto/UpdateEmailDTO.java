package pt.luis.blogapp.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateEmailDTO(

        @Email(message = "Invalid email format!")
        String currentEmail,

        @Email(message = "Invalid email format!")
        String newEmail
) {}
