package pt.luis.blogapp.api.services.serviceImpl;

import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.repositories.UserRepository;
import pt.luis.blogapp.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){

        this.userRepository = userRepository;
    }
}
