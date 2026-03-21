package pt.luis.blogapp.api.dto.categoryDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryDTO(

        @NotBlank
        @Size(max = 20)
        String name,

        @NotBlank
        @Size(max = 60)
        String description
) {
}
