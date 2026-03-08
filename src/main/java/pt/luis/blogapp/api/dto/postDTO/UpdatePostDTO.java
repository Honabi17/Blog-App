package pt.luis.blogapp.api.dto.postDTO;

import jakarta.validation.constraints.Size;
import pt.luis.blogapp.api.models.entities.User;

public record UpdatePostDTO(

        @Size(max = 25)
        String title,

        String content,

        User author
) {
}
