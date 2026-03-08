package pt.luis.blogapp.api.mappers;


import pt.luis.blogapp.api.dto.userDTO.CreateUserDTO;
import pt.luis.blogapp.api.dto.userDTO.UserResponseDTO;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.infrastructure.securities.password.Password;

public class UserMapper {

    public static User toEntity(CreateUserDTO dto){

        if(dto == null){
            return null;
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());

        return user;
    }



    public static UserResponseDTO toResponse(User user){

        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getLastLoginAt() != null ? user.getLastLoginAt().toString() : "First Login!"
        );
    }

    public static void updateEmail(User user, String newEmail) {

        if(user == null || newEmail == null) {
           return;
        }

        user.setEmail(newEmail);
    }

    public static void updatePassword(User user, String hashedPassword){
        if(user == null || hashedPassword == null){
            return;
        }

        user.setPassword(new Password(hashedPassword));
    }
}
