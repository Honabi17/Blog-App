package pt.luis.blogapp.api.dto;

public record UserUpdateDTO(
        String email,
        String password
) {}
