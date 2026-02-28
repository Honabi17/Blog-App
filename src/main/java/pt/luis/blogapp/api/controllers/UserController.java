package pt.luis.blogapp.api.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.luis.blogapp.api.entities.User;
import pt.luis.blogapp.api.services.UserService;

@RestController
@RequestMapping("/api/blog ")
public class UserController{

    private UserService userService;

    public UserController(UserService userService){

        this.userService = userService;
    }
}
