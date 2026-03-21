package pt.luis.blogapp.api.mappers;

import pt.luis.blogapp.api.dto.commentDTO.CreateCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.ResponseCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.UpdateCommentDTO;
import pt.luis.blogapp.api.models.entities.Comment;
import pt.luis.blogapp.api.models.entities.Post;
import pt.luis.blogapp.api.models.entities.User;

import java.time.LocalDateTime;

public class CommentMapper {

    public static Comment toEntity(CreateCommentDTO dto, User author, Post post){
        Comment comment = new Comment();
        comment.setContent(dto.content());
        comment.setAuthor(author);
        comment.setPost(post);
        return comment;
    }

    public static void updateEntity(UpdateCommentDTO dto, Comment comment){

        if(dto.content() != null && !dto.content().isBlank()){
            comment.setContent(dto.content());
        }
        comment.setUpdatedAt(LocalDateTime.now());
    }

    public static ResponseCommentDTO toDTO(Comment comment){
        return new ResponseCommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor() != null ? comment.getAuthor().getId() : null,
                comment.getAuthor() != null ? comment.getAuthor().getUsername() : null,
                comment.getPost() != null ? comment.getPost().getId() : null,
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
