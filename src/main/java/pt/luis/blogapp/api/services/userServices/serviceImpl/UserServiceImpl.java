package pt.luis.blogapp.api.services.userServices.serviceImpl;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.userDTO.ResponseUserDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdateEmailDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdatePasswordDTO;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.dto.userDTO.UpdateRoleDTO;
import pt.luis.blogapp.api.models.role.UserRole;
import pt.luis.blogapp.api.exceptions.Exceptions.BadRequestException;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceNotFoundException;
import pt.luis.blogapp.api.exceptions.Exceptions.UserValidationException;
import pt.luis.blogapp.api.infrastructure.securities.password.Password;
import pt.luis.blogapp.api.infrastructure.securities.password.PasswordHasher;
import pt.luis.blogapp.api.mappers.UserMapper;
import pt.luis.blogapp.api.repositories.userRepositories.UserRepository;
import pt.luis.blogapp.api.services.userServices.UserService;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordHasher passwordHasher)
    {

        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public ResponseUserDTO getByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public ResponseUserDTO getByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public ResponseUserDTO getByRole(UserRole role) {

        User user = userRepository.findByRole(role)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        return UserMapper.toResponse(user);
    }


    @Override
    public ResponseUserDTO updateEmail(String currentEmail, UpdateEmailDTO dto) {

        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found!"));

        String newEmail = Optional.ofNullable(dto.newEmail())
                .filter(e -> !e.isBlank())
                .orElseThrow(() -> new BadRequestException("The new email is not valid!"));

        Optional.of(newEmail)
                .filter(e -> !e.equalsIgnoreCase(currentEmail))
                .orElseThrow(() -> new BadRequestException("The new email can't be the same as current!"));

        Optional.ofNullable(newEmail)
                .filter(e -> !userRepository.existsByEmail(e))
                .orElseThrow(() -> new BadRequestException("This email already exists"));

        user.setEmail(newEmail);
        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    @Override
    public ResponseUserDTO updatePassword(UpdatePasswordDTO dto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(!passwordHasher.matches(dto.currentPassword(), user.getPassword().getHash())){
            throw new BadRequestException("Current password is incorrect");
        }

        String newPassword = Optional.ofNullable(dto.newPassword())
                .filter(p -> !p.isBlank())
                .orElseThrow(() -> new BadRequestException("The new password is not valid!"));

        if(passwordHasher.matches(newPassword, user.getPassword().getHash())){
            throw new BadRequestException("The new password can't be the same as current!");
        }

        String hashed = passwordHasher.hash(newPassword);
        user.setPassword(new Password(hashed));

        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    @Override
    public ResponseUserDTO updateRole(UpdateRoleDTO dto) {

        String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        User admin = userRepository.findByUsername(loggedUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Logger user not found!"));

        if(admin.getRole() != UserRole.ADMIN){
            throw new UserValidationException("Only ADMIN can change roles!");
        }

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setRole(dto.newRole());
        User saved = userRepository.save(user);

        return UserMapper.toResponse(saved);
    }
}
