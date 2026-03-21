package pt.luis.blogapp.api.controllers;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.commentDTO.CreateCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.ResponseCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.UpdateCommentDTO;
import pt.luis.blogapp.api.services.commentService.CommentService;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){

        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<ResponseCommentDTO> Create(@Valid @RequestBody CreateCommentDTO dto){

        ResponseCommentDTO create = commentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<ResponseCommentDTO>> getByAuthor(@PathVariable String author){

        List<ResponseCommentDTO> result = commentService.getByAuthor(author);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseCommentDTO>> getAllPaged(
            @RequestParam Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ){
        Page<ResponseCommentDTO> result = commentService.getAllPaged(
                postId,
                page,
                size,
                sortBy,
                direction
        );
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCommentDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentDTO dto
    ){
        ResponseCommentDTO update = commentService.update(id, dto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
