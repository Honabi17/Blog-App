package pt.luis.blogapp.api.mappers;

import pt.luis.blogapp.api.dto.postDTO.*;
import pt.luis.blogapp.api.models.entities.Category;
import pt.luis.blogapp.api.models.entities.Post;
import pt.luis.blogapp.api.models.entities.User;

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

    public static Post toEntity(CreatePostDTO dto, User author){

        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setAuthor(author);

        return post;
    }

    public static void updatePostEntity(UpdatePostDTO dto, Post post){

        if(dto.title() != null && !dto.title().isBlank()){
            post.setTitle(dto.title());
        }

        if (dto.content() != null && !dto.content().isBlank()){
            post.setContent(dto.content());
        }
    }
}
