package pt.luis.blogapp.api.dto.categoryDTO;

import java.time.LocalDateTime;

public record CategoryResponseDTO(
        Long id,
        String name,
        String description,
        Long createdById,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
