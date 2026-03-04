package pt.luis.blogapp.api.dto.userDTO;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message = "Username is required!")
        String username,

        @NotBlank(message = "password is required!")
        String password
) {
}
