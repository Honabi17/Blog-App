package pt.luis.blogapp.api.dto.postDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreatePostDTO(

        @NotBlank
        @Size(max = 25)
        String title,

        @NotBlank
        String content
) {}
