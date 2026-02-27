package pt.luis.blogapp.api.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.luis.blogapp.api.entities.User;

@Repository
public interface UserController extends JpaRepository <User, Long> {
}
