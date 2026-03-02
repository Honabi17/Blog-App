package pt.luis.blogapp.api.services.serviceImpl;

import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.AuthResponseDTO;
import pt.luis.blogapp.api.dto.CreateUserDTO;
import pt.luis.blogapp.api.dto.LoginRequestDTO;
import pt.luis.blogapp.api.dto.ResponseUserDTO;
import pt.luis.blogapp.api.entities.User;
import pt.luis.blogapp.api.exceptions.ResourceNotFoundException;
import pt.luis.blogapp.api.exceptions.UserValidationException;
import pt.luis.blogapp.api.infrastructure.security.CustomUserDetails;
import pt.luis.blogapp.api.infrastructure.security.JwtService;
import pt.luis.blogapp.api.infrastructure.security.Password;
import pt.luis.blogapp.api.infrastructure.security.PasswordHasher;
import pt.luis.blogapp.api.mappers.UserMapper;
import pt.luis.blogapp.api.repositories.UserRepository;
import pt.luis.blogapp.api.services.UserAuthService;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;

    public UserAuthServiceImpl(
            UserRepository userRepository,
            PasswordHasher passwordHasher,
            JwtService jwtService
    ){
        this.userRepository =  userRepository;
        this.passwordHasher = passwordHasher;
        this.jwtService = jwtService;
    }


    @Override
    public ResponseUserDTO created(CreateUserDTO dto) {

        Optional.ofNullable(dto.username())
                .filter(u -> !u.isBlank())
                .orElseThrow(() -> new UserValidationException("Username can't be null or empty!"));

        Optional.ofNullable(dto.email())
                .filter(e -> !e.isBlank())
                .orElseThrow(() -> new UserValidationException("Email can't be null or empty"));

        userRepository.findByUsername(dto.username())
                .ifPresent(u -> {
                    throw new UserValidationException("Username already exists!");
                });

        userRepository.findByEmail(dto.email())
                .ifPresent(e -> {
                    throw new UserValidationException("Email already exists!");
                });

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(Password.fromPlainText(dto.password()));
        user.setEmail(dto.email());

        User saved = userRepository.save(user);

       return UserMapper.toResponse(saved);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {

       User user = userRepository.findByUsername(dto.username())
               .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));

       if(!PasswordHasher.matches(dto.password(), user.getPassword().getHash())){
           throw new ResourceNotFoundException("Invalid credentials!");
       }

       CustomUserDetails userDetails = new CustomUserDetails(user);

       String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }
}
