package pt.luis.blogapp.api.controllers.postController;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.postDTO.CreatePostDTO;
import pt.luis.blogapp.api.dto.postDTO.PostResponseDTO;
import pt.luis.blogapp.api.dto.postDTO.UpdatePostDTO;
import pt.luis.blogapp.api.services.postService.PostService;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {


    private final PostService postService;

    public PostController(PostService postService){

        this.postService = postService;
    }


    @PostMapping("/")
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody CreatePostDTO dto){

        PostResponseDTO create = postService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<PostResponseDTO> getByTitle(
            @PathVariable String title){

        PostResponseDTO result = postService.getByTitle(title);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<PostResponseDTO>> getByAuthor(@PathVariable String author){

        List<PostResponseDTO> result = postService.getByAuthor(author);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ){
        Page<PostResponseDTO> result = postService.getAllPaged(
                page, size, sortBy, direction
        );
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePostDTO dto)
    {
        PostResponseDTO update = postService.update(id, dto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
