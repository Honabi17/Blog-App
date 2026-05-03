package pt.luis.blogapp.api.services.statsService;

import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.statsDTO.DashboardStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.EarningStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.RecentPostDTO;
import pt.luis.blogapp.api.dto.statsDTO.TrafficStatsDTO;
import pt.luis.blogapp.api.models.entities.Post;
import pt.luis.blogapp.api.repositories.CategoryRepository;
import pt.luis.blogapp.api.repositories.CommentRepository;
import pt.luis.blogapp.api.repositories.PostRepository;
import pt.luis.blogapp.api.repositories.userRepositories.UserRepository;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class DashboardServiceImpl implements DashboardService{

    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public DashboardServiceImpl(
            CategoryRepository categoryRepository,
            PostRepository postRepository,
            CommentRepository commentRepository,
            UserRepository userRepository
    ){
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DashboardStatsDTO getStats() {

        Random random = new Random();

        long categories = categoryRepository.count();
        long posts = postRepository.count();
        long comments = commentRepository.count();
        long users = userRepository.count();

        long pageviews = 5000 + random.nextInt(15000);
        long visitors = 1000 + random.nextInt(5000);

        return new DashboardStatsDTO(
                pageviews,
                visitors,
                categories,
                posts,
                comments
        );
    }

    @Override
    public List<TrafficStatsDTO> getTrafficStats() {

        List<TrafficStatsDTO> stats = new ArrayList<>();

        YearMonth current = YearMonth.now();
        Random random = new Random();

        for(int i = 11; i >= 0; i--){
            YearMonth month = current.minusMonths(i);

            long visits = 500 + random.nextInt(1500);

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
        Random random = new Random();

        for (int i = 11; i >= 0; i--){
            YearMonth month = current.minusMonths(i);

            double amount = 50 + random.nextDouble() * 200;

            earningStats.add(new EarningStatsDTO(
                    month.getMonth()
                            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    Math.round(amount * 100.0) / 100.0
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
