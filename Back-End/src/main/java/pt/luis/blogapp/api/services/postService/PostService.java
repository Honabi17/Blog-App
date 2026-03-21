package pt.luis.blogapp.api.services.postService;


import org.springframework.data.domain.Page;
import pt.luis.blogapp.api.dto.postDTO.CreatePostDTO;
import pt.luis.blogapp.api.dto.postDTO.PostResponseDTO;
import pt.luis.blogapp.api.dto.postDTO.UpdatePostDTO;
import java.util.List;


public interface PostService {

    PostResponseDTO create(CreatePostDTO dto);

    List<PostResponseDTO> getByTitle(String title);
    List<PostResponseDTO> getByAuthor(String author);
    Page<PostResponseDTO> getAllPaged(
            int page, int size, String sortBy, String direction
    );

    PostResponseDTO update(Long id, UpdatePostDTO dto);

    void delete(Long id);
}
