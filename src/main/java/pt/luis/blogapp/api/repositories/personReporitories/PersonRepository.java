package pt.luis.blogapp.api.repositories.personReporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.luis.blogapp.api.models.entities.Person;
import pt.luis.blogapp.api.models.entities.User;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByUser(User user);
    Optional<Person> findByUser(User user);
}
