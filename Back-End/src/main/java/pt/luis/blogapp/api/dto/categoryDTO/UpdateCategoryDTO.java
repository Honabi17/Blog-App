package pt.luis.blogapp.api.dto.categoryDTO;

import jakarta.validation.constraints.Size;

public record UpdateCategoryDTO(

        @Size(max = 20)
        String name,

        @Size(max = 60)
        String description
) {
}
