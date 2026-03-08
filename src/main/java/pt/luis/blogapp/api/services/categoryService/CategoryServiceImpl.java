package pt.luis.blogapp.api.services.categoryService;


import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.categoryDTO.CategoryResponseDTO;
import pt.luis.blogapp.api.dto.categoryDTO.CreateCategoryDTO;
import pt.luis.blogapp.api.dto.categoryDTO.UpdateCategoryDTO;
import pt.luis.blogapp.api.exceptions.Exceptions.AccessDeniedException;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceAlreadyExistsException;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceNotFoundException;
import pt.luis.blogapp.api.mappers.CategoryMapper;
import pt.luis.blogapp.api.models.entities.Category;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.models.role.UserRole;
import pt.luis.blogapp.api.repositories.CategoryRepository;
import pt.luis.blogapp.api.repositories.userRepositories.UserRepository;
import pt.luis.blogapp.api.services.userServices.UserAuthService;
import java.util.List;



@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final UserAuthService authService;
    private final UserRepository userRepository;



    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            UserAuthService authService,
            UserRepository userRepository
    ){
        this.categoryRepository = categoryRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    private User currentUser(){

        return authService.getAuthenticatedUser();
    }

    private void ensureCategoryDoesNotExists(String name){
        if(categoryRepository.existsByName(name)){
            throw new ResourceAlreadyExistsException("Category already exists: " + name);
        }
    }

    private void ensureUserCanModifyCategory(User user, Category category){
        boolean isCreator = category.getCreatedBy().getId().equals(user.getId());
        boolean isModerator = user.getRole().equals(UserRole.MODERATOR);
        boolean isAdmin = user.getRole().equals(UserRole.ADMIN);

        if(!(isCreator || isModerator || isAdmin)){
            throw new AccessDeniedException("You are not allowed to modify this category");
        }
    }

    private Category findCategoryOrThrow(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }



    @Override
    public CategoryResponseDTO created(CreateCategoryDTO dto) {

        ensureCategoryDoesNotExists(dto.name());

        User creator = currentUser();

        Category category = CategoryMapper.toEntity(dto, creator);

        categoryRepository.save(category);

        return CategoryMapper.toDTO(category);
    }

    @Override
    public CategoryResponseDTO getByName(String name) {

        Category find = categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + name));

        return CategoryMapper.toDTO(find);
    }

    @Override
    public List<CategoryResponseDTO> getByCreator(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        List<Category> categories = categoryRepository.findAllByCreatedBy(user);

        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("User has no categories: " + username);
        }

        return categories.stream()
                .map(CategoryMapper::toDTO)
                .toList();
    }

    @Override
    public List<CategoryResponseDTO> getAll() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryMapper::toDTO)
                .toList();
    }

    @Override
    public CategoryResponseDTO updated(Long id, UpdateCategoryDTO dto) {

        Category updateCategory = findCategoryOrThrow(id);

        User user = currentUser();

        ensureUserCanModifyCategory(user, updateCategory);

        CategoryMapper.updateCategoryEntity(dto, updateCategory);

        categoryRepository.save(updateCategory);

        return CategoryMapper.toDTO(updateCategory);
    }

    @Override
    public void deleted(Long id) {

        Category deleteCategory = findCategoryOrThrow(id);

        User user = currentUser();

        ensureUserCanModifyCategory(user, deleteCategory);

        categoryRepository.delete(deleteCategory);
    }
}
