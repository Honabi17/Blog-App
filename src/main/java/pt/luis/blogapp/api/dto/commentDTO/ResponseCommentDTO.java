package pt.luis.blogapp.api.dto.commentDTO;

import java.time.LocalDateTime;

public record ResponseCommentDTO(

        Long id,
        String content,
        Long authorId,
        String authorUsername,
        Long postId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}
