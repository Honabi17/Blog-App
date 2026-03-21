package pt.luis.blogapp.api.dto.postDTO;

import pt.luis.blogapp.api.models.entities.Category;

public record CategorySummaryDTO(

        Long id,
        String name
) {
}
