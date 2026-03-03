package pt.luis.blogapp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.luis.blogapp.api.entities.PasswordResetToken;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository <PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);
}
