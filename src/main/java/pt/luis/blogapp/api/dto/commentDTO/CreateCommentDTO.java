package pt.luis.blogapp.api.dto.commentDTO;

public record CreateCommentDTO(
        String content,
        Long postId
) {
}
