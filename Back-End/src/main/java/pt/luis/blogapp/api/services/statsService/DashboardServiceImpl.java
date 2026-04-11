package pt.luis.blogapp.api.services.statsService;

import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.statsDTO.DashboardStatsDTO;
import pt.luis.blogapp.api.repositories.CommentRepository;
import pt.luis.blogapp.api.repositories.PostRepository;
import pt.luis.blogapp.api.repositories.userRepositories.UserRepository;

@Service
public class DashboardServiceImpl implements DashboardService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public DashboardServiceImpl(
            PostRepository postRepository,
            CommentRepository commentRepository,
            UserRepository userRepository
    ){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DashboardStatsDTO getStats() {

        long posts = postRepository.count();
        long comment = commentRepository.count();
        long users = userRepository.count();

        long pageviews = 0;
        long visitors = 0;

        return new DashboardStatsDTO(pageviews, visitors, posts, comment);
    }
}
