package pt.luis.blogapp.api.services.commentService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.commentDTO.CreateCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.ResponseCommentDTO;
import pt.luis.blogapp.api.dto.commentDTO.UpdateCommentDTO;
import pt.luis.blogapp.api.exceptions.Exceptions.AccessDeniedException;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceNotFoundException;
import pt.luis.blogapp.api.mappers.CommentMapper;
import pt.luis.blogapp.api.models.entities.Comment;
import pt.luis.blogapp.api.models.entities.Post;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.models.role.UserRole;
import pt.luis.blogapp.api.repositories.CommentRepository;
import pt.luis.blogapp.api.repositories.PostRepository;
import pt.luis.blogapp.api.services.userServices.UserAuthService;
import java.util.List;



@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserAuthService authService;
    private final PostRepository postRepository;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            UserAuthService authService,
            PostRepository postRepository
    ){
        this.commentRepository = commentRepository;
        this.authService = authService;
        this.postRepository = postRepository;
    }


    private User currentUser(){
        return authService.getAuthenticatedUser();
    }

    private Comment findCommentOrThrow(Long id){

        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
    }

    private void ensureUserCanModifyComment(User user, Comment comment){
        boolean isCreator = comment.getAuthor().getId().equals(user.getId());
        boolean isModerator = user.getRole().equals(UserRole.MODERATOR);
        boolean isAdmin = user.getRole().equals(UserRole.ADMIN);

        if(!(isCreator || isModerator || isAdmin)){
            throw new AccessDeniedException("You are not allowed to modify this comment");
        }
    }



    @Override
    public ResponseCommentDTO create(CreateCommentDTO dto) {

        User author = currentUser();

        Post findPost = postRepository.findById(dto.postId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post not found with id: " + dto.postId()
                ));

        Comment created = CommentMapper.toEntity(dto, author, findPost);

        Comment saved = commentRepository.save(created);

        return CommentMapper.toDTO(saved);
    }


    @Override
    public List<ResponseCommentDTO> getByAuthor(String author) {

        List<Comment> comments = commentRepository.findAllByAuthorUsername(author);

        if(comments.isEmpty()){
            throw new ResourceNotFoundException("Author has not comments");
        }

        return comments.stream()
                .map(CommentMapper::toDTO)
                .toList();
    }

    @Override
    public Page<ResponseCommentDTO> getAllPaged(Long postId, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);

        return comments.map(CommentMapper::toDTO);
    }

    @Override
    public ResponseCommentDTO update(Long id, UpdateCommentDTO dto) {

        User user = currentUser();

        Comment updateComment = findCommentOrThrow(id);

        ensureUserCanModifyComment(user, updateComment);

        CommentMapper.updateEntity(dto, updateComment);

        commentRepository.save(updateComment);

        return CommentMapper.toDTO(updateComment);
    }

    @Override
    public void delete(Long id) {

        User user = currentUser();

        Comment comment = findCommentOrThrow(id);

        ensureUserCanModifyComment(user, comment);

        commentRepository.delete(comment);
    }
}
