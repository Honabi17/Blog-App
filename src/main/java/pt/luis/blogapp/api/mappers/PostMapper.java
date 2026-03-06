package pt.luis.blogapp.api.mappers;

import pt.luis.blogapp.api.dto.postDTO.CategorySummaryDTO;
import pt.luis.blogapp.api.dto.postDTO.PostResponseDTO;
import pt.luis.blogapp.api.dto.postDTO.UserSummaryDTO;
import pt.luis.blogapp.api.models.entities.Post;

public class PostMapper {

    public static PostResponseDTO toDTO(Post post){

        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                new UserSummaryDTO(
                        post.getAuthor().getId(),
                        post.getAuthor().getUsername()
                ),
                new CategorySummaryDTO(
                        post.getCategory().getId(),
                        post.getCategory().getName()
                ),
                post.getComments() != null ? post.getComments().size() : 0,
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
