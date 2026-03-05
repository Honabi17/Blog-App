package pt.luis.blogapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.luis.blogapp.api.models.entities.Category;
import pt.luis.blogapp.api.models.entities.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
    Optional<Category> findByName(String name);
    List<Category> findAllByCreatedBy(User user);

}