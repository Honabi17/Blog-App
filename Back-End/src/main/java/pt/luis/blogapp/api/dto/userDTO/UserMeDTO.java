package pt.luis.blogapp.api.dto.userDTO;

public record UserMeDTO(
        Long id,
        String username,
        String email,
        String role
) {}
