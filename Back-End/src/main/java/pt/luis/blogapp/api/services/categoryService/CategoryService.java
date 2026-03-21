package pt.luis.blogapp.api.services.categoryService;

import pt.luis.blogapp.api.dto.categoryDTO.CategoryResponseDTO;
import pt.luis.blogapp.api.dto.categoryDTO.CreateCategoryDTO;
import pt.luis.blogapp.api.dto.categoryDTO.UpdateCategoryDTO;
import pt.luis.blogapp.api.models.entities.Category;
import java.util.List;


public interface CategoryService {

    CategoryResponseDTO created(CreateCategoryDTO dto);

    CategoryResponseDTO getByName(String name);
    List<CategoryResponseDTO> getByCreator(String username);
    List<CategoryResponseDTO> getAll();

    CategoryResponseDTO updated(Long id, UpdateCategoryDTO dto);

    void deleted(Long id);

    Category findById(Long id);
}
