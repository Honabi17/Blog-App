package pt.luis.blogapp.api.mappers;

import pt.luis.blogapp.api.dto.categoryDTO.CategoryResponseDTO;
import pt.luis.blogapp.api.dto.categoryDTO.CreateCategoryDTO;
import pt.luis.blogapp.api.dto.categoryDTO.UpdateCategoryDTO;
import pt.luis.blogapp.api.models.entities.Category;
import pt.luis.blogapp.api.models.entities.User;

public class CategoryMapper {

    public static CategoryResponseDTO toDTO(Category category){

        Long creatorId = category.getCreatedBy() != null ?
                category.getCreatedBy().getId() : null;

        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                creatorId,
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public static Category toEntity(CreateCategoryDTO dto, User creator){

        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());
        category.setCreatedBy(creator);
        return category;
    }

    public static void updateCategoryEntity(UpdateCategoryDTO dto, Category category){

        if(dto.name() != null && !dto.name().isBlank()){
            category.setName(dto.name());
        }

        if (dto.description() != null && !dto.description().isBlank()){
            category.setDescription(dto.description());
        }

    }
}
