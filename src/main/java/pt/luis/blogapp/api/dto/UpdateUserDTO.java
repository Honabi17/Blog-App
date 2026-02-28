package pt.luis.blogapp.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(

        @Email(message = "Invalid email format!")
        String email,

        @Size(min = 4, message = "Password must have at least 4 characters!")
        String password
) {}
