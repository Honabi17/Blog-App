package pt.luis.blogapp.api.dto.postDTO;

import pt.luis.blogapp.api.models.entities.User;

public record UserSummaryDTO(
        Long id,
        String username
) {}