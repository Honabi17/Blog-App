package pt.luis.blogapp.api.dto.postDTO;

import pt.luis.blogapp.api.dto.categoryDTO.CategorySummaryDTO;

import java.time.LocalDateTime;


public record PostResponseDTO(

        Long id,
        String title,
        String content,
        UserSummaryDTO author,
        CategorySummaryDTO category,
        int commentCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
