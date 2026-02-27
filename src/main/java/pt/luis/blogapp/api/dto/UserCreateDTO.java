package pt.luis.blogapp.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(

        @NotBlank(message = "Username is required!")
        String username,

        @NotBlank(message = "Email is required!")
        @Email(message = "Invalid email format!")
        @Email String email,

        @NotBlank(message = "Password is required!")
        @Size(min = 4)
        String password
) {}
