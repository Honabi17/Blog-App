package pt.luis.blogapp.api.repositories.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.models.role.UserRole;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByRole(UserRole role);

    boolean existsByEmail(String email);

}
