package pt.luis.blogapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.luis.blogapp.api.dto.ResponseUserDTO;
import pt.luis.blogapp.api.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
