package pt.luis.blogapp.api.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.luis.blogapp.api.models.entities.Post;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle (String title);

    List<Post> findAllByTitleContainingIgnoreCase(String title);

    List<Post> findAllByAuthor_Username(String username);

    Page<Post> findAll(Pageable pageable);

    List<Post> findTop5ByOrderByCreatedAtDesc();

}
