package pt.luis.blogapp.api.controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.categoryDTO.CategoryResponseDTO;
import pt.luis.blogapp.api.dto.categoryDTO.CreateCategoryDTO;
import pt.luis.blogapp.api.dto.categoryDTO.UpdateCategoryDTO;
import pt.luis.blogapp.api.services.categoryService.CategoryService;
import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<CategoryResponseDTO> created(@Valid @RequestBody CreateCategoryDTO dto){

        CategoryResponseDTO create = categoryService.created(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponseDTO> getByName(@PathVariable String name){

        CategoryResponseDTO dto = categoryService.getByName(name);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<CategoryResponseDTO>> getByCreator(@PathVariable String username){

        List<CategoryResponseDTO> list = categoryService.getByCreator(username);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAll(){

      List<CategoryResponseDTO> list = categoryService.getAll();
      return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryDTO dto){

        CategoryResponseDTO update = categoryService.updated(id, dto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        categoryService.deleted(id);
        return ResponseEntity.noContent().build();
    }
}
