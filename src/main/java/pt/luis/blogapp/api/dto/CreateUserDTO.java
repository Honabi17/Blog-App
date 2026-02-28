package pt.luis.blogapp.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(

        @NotBlank(message = "Username is required!")
        String username,

        @NotNull(message= "Email is required!")
        @NotBlank(message = "Email cannot be empty!")
        @Email(message = "Invalid email format!")
        String email,

        @NotBlank(message = "Password is required!")
        @Size(min = 4)
        String password
) {}
