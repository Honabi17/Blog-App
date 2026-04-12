package pt.luis.blogapp.api.services.statsService;

import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.statsDTO.DashboardStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.EarningStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.RecentPostDTO;
import pt.luis.blogapp.api.dto.statsDTO.TrafficStatsDTO;
import pt.luis.blogapp.api.models.entities.Post;
import pt.luis.blogapp.api.repositories.CommentRepository;
import pt.luis.blogapp.api.repositories.PostRepository;
import pt.luis.blogapp.api.repositories.userRepositories.UserRepository;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @Override
    public List<TrafficStatsDTO> getTrafficStats() {

        List<TrafficStatsDTO> stats = new ArrayList<>();

        YearMonth current = YearMonth.now();

        for(int i = 11; i >= 0; i--){
            YearMonth month = current.minusMonths(i);
            stats.add(new TrafficStatsDTO(
                    month.getMonth()
                            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH), 0));
        }
        return stats;
    }

    @Override
    public List<EarningStatsDTO> getEarningStats() {

        List<EarningStatsDTO> earningStats = new ArrayList<>();

        YearMonth current = YearMonth.now();

        for (int i = 11; i >= 0; i--){
            YearMonth month = current.minusMonths(i);

            earningStats.add(new EarningStatsDTO(
                    month.getMonth()
                            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH), 0.0
            ));
        }
        return earningStats;
    }

    @Override
    public List<RecentPostDTO> getRecentPosts() {

        List<Post> posts = postRepository.findTop5ByOrderByCreatedAtDesc();

        return posts.stream()
                .map(p -> new RecentPostDTO(
                        p.getId(),
                        p.getTitle(),
                        p.getCreatedAt()
                ))
                .toList();
    }
}
