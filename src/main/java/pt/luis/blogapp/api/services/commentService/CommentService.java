package pt.luis.blogapp.api.services.commentService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import pt.luis.blogapp.api.dto.commentDTO.CreateCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.ResponseCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.UpdateCommentDTO;

import java.util.List;

public interface CommentService {

    ResponseCommentDTO create(CreateCommentDTO dto);

    List<ResponseCommentDTO> getByAuthor(String author);
    Page<ResponseCommentDTO> getAllPaged(
            Long postId,
            int page,
            int size,
            String sortBy,
            String direction
    );
    ResponseCommentDTO update(Long id, UpdateCommentDTO dto);
    void delete(Long id);
}
