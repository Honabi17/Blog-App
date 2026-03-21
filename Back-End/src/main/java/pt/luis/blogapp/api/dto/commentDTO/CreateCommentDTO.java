package pt.luis.blogapp.api.dto.commentDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentDTO(

        @NotBlank
        @Size(min = 4 , max = 500)
        String content,

        @NotNull
        Long postId
) {}
