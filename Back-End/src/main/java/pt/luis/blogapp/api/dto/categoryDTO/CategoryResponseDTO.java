package pt.luis.blogapp.api.dto.categoryDTO;

import pt.luis.blogapp.api.dto.postDTO.UserSummaryDTO;

import java.time.LocalDateTime;

public record CategoryResponseDTO(
        Long id,
        String name,
        String description,
        UserSummaryDTO createdBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
