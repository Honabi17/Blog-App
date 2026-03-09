package pt.luis.blogapp.api.services.postService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.postDTO.CreatePostDTO;
import pt.luis.blogapp.api.dto.postDTO.PostResponseDTO;
import pt.luis.blogapp.api.dto.postDTO.UpdatePostDTO;
import pt.luis.blogapp.api.exceptions.Exceptions.AccessDeniedException;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceAlreadyExistsException;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceNotFoundException;
import pt.luis.blogapp.api.mappers.PostMapper;
import pt.luis.blogapp.api.models.entities.Category;
import pt.luis.blogapp.api.models.entities.Post;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.models.role.UserRole;
import pt.luis.blogapp.api.repositories.PostRepository;
import pt.luis.blogapp.api.services.categoryService.CategoryService;
import pt.luis.blogapp.api.services.userServices.UserAuthService;
import java.util.List;



@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserAuthService authService;
    private final CategoryService categoryService;


    public PostServiceImpl(
            PostRepository postRepository,
            UserAuthService authService,
            CategoryService categoryService
    ){
        this.postRepository = postRepository;
        this.authService = authService;
        this.categoryService = categoryService;
    }

    private  User currentUser(){

        return authService.getAuthenticatedUser();
    }

    private void ensureUserCanModifyPost(User user, Post post){
        boolean isCreator = post.getAuthor().getId().equals(user.getId());
        boolean isModerator = user.getRole().equals(UserRole.MODERATOR);
        boolean isAdmin = user.getRole().equals(UserRole.ADMIN);

        if(!(isCreator || isModerator || isAdmin)){
            throw new AccessDeniedException("You are not allowed to modify this post");
        }
    }

    private void ensurePostDoesNotExists(String title){
        if(postRepository.findByTitle(title).isPresent()){
            throw new ResourceAlreadyExistsException("This post already exists: " + title);
        }
    }

    private Post findPostOrThrow(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }



    @Override
    public PostResponseDTO create(CreatePostDTO dto) {

        ensurePostDoesNotExists(dto.title());

        User user = currentUser();

        Category category = categoryService.findById(dto.categoryId());

        Post post = PostMapper.toEntity(dto, user);

        post.setCategory(category);
        postRepository.save(post);

        return PostMapper.toDTO(post);
    }

    @Override
    public List<PostResponseDTO> getByTitle(String title) {

        List<Post> list = postRepository.findAllByTitleContainingIgnoreCase(title);

        if(list.isEmpty()){
            throw new ResourceNotFoundException("Post not found with title: " + title);
        }

        return list.stream()
                .map(PostMapper::toDTO)
                .toList();
    }

    @Override
    public List<PostResponseDTO> getByAuthor(String author) {

        List<Post> posts = postRepository.findAllByAuthor_Username(author);

        if(posts.isEmpty()){
            throw new ResourceNotFoundException("Author has not posts: " + author);
        }

        return posts.stream()
                .map(PostMapper::toDTO)
                .toList();
    }

    @Override
    public Page<PostResponseDTO> getAllPaged(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        return posts.map(PostMapper::toDTO);
    }

    @Override
    public PostResponseDTO update(Long id, UpdatePostDTO dto) {

        Post updatePost = findPostOrThrow(id);

        User user = currentUser();

        ensureUserCanModifyPost(user, updatePost);

        PostMapper.updatePostEntity(dto, updatePost);

        if(dto.categoryId() != null){
            Category category = categoryService.findById(dto.categoryId());
            updatePost.setCategory(category);
        }

        postRepository.save(updatePost);

        return PostMapper.toDTO(updatePost);
    }

    @Override
    public void delete(Long id) {

        Post deletePost = findPostOrThrow(id);

        User user = currentUser();

        ensureUserCanModifyPost(user, deletePost);

        postRepository.delete(deletePost);
    }
}
