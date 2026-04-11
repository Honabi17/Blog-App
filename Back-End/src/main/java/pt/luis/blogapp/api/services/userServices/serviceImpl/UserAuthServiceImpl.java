package pt.luis.blogapp.api.services.userServices.serviceImpl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pt.luis.blogapp.api.dto.userDTO.*;

import pt.luis.blogapp.api.infrastructure.securities.password.Password;
import pt.luis.blogapp.api.infrastructure.securities.password.PasswordHasher;
import pt.luis.blogapp.api.infrastructure.securities.security.CustomUserDetails;
import pt.luis.blogapp.api.infrastructure.securities.security.JwtService;
import pt.luis.blogapp.api.models.entities.PasswordResetToken;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.models.role.UserRole;
import pt.luis.blogapp.api.exceptions.Exceptions.BadRequestException;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceNotFoundException;
import pt.luis.blogapp.api.exceptions.Exceptions.UserValidationException;

import pt.luis.blogapp.api.mappers.UserMapper;
import pt.luis.blogapp.api.repositories.userRepositories.UserRepository;
import pt.luis.blogapp.api.repositories.userRepositories.TokenRepository;
import pt.luis.blogapp.api.services.userServices.EmailService;
import pt.luis.blogapp.api.services.userServices.UserAuthService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final EmailService emailService;

    public UserAuthServiceImpl(
            UserRepository userRepository,
            PasswordHasher passwordHasher,
            TokenRepository tokenRepository,
            JwtService jwtService,
            EmailService emailService
    ){
        this.userRepository =  userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    private void ensureUsernameAvailable(String username){
        userRepository.findByUsername(username)
                .ifPresent(u -> {
                    throw new UserValidationException("Username already exists!");
                });
    }

    private void ensureEmailAvailable(String email){
        userRepository.findByEmail(email)
                .ifPresent(e -> {
                    throw new UserValidationException("Email already exists!");
                });
    }

    private User findByUsernameOrThrow(String username){
        return  userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials!"));
    }


    @Override
    public UserResponseDTO created(CreateUserDTO dto) {

        Optional.ofNullable(dto.username())
                .filter(u -> !u.isBlank())
                .orElseThrow(() -> new UserValidationException("Username can't be null or empty!"));

        Optional.ofNullable(dto.email())
                .filter(e -> !e.isBlank())
                .orElseThrow(() -> new UserValidationException("Email can't be null or empty"));

        ensureUsernameAvailable(dto.username());
        ensureEmailAvailable(dto.email());

        String hashed = passwordHasher.hash(dto.password());

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(new Password(hashed));
        user.setEmail(dto.email());

        UserRole role = userRepository.count() == 0 ? UserRole.ADMIN : UserRole.USER;
        user.setRole(role);

        User saved = userRepository.save(user);

       return UserMapper.toResponse(saved);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {

       User user = findByUsernameOrThrow(dto.username());

       if(!passwordHasher.matches(dto.password(), user.getPassword().getHash())){
           throw new ResourceNotFoundException("Invalid credentials!");
       }

       CustomUserDetails userDetails = new CustomUserDetails(user);

       String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }

    @Override
    public String requestReset(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found!"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken(
                token,
                user,
                LocalDateTime.now().plusMinutes(15)
        );

        tokenRepository.save(resetToken);

        emailService.sendPasswordResetEmail(user.getEmail(), token);

        return "Password reset email sent!";
    }

    @Override
    public String confirmPassword(ResetPasswordConfirmDTO dto) {

        PasswordResetToken resetToken = tokenRepository.findByToken(dto.token())
                .orElseThrow(() -> new BadRequestException("Invalid token"));

        if(resetToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Token expired");
        }

        User user = resetToken.getUser();

        String hashed = passwordHasher.hash(dto.newPassword());
        user.setPassword(new Password(hashed));
        userRepository.save(user);

        tokenRepository.delete(resetToken);

        return "Password updated successfully!";
    }

    @Override
    public User getAuthenticatedUser() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null){
            throw  new RuntimeException("No authenticated user found");
        }

        if(auth.getPrincipal() instanceof CustomUserDetails userDetails){
            return userDetails.getUser();
        }

        return findByUsernameOrThrow(auth.getName());
    }

    @Override
    public UserMeDTO getCurrentUser() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserMapper.toMeDTO(user);
    }
}
