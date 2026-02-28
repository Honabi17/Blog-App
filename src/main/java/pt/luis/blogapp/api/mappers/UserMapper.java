package pt.luis.blogapp.api.mappers;


import pt.luis.blogapp.api.dto.CreateUserDTO;
import pt.luis.blogapp.api.dto.ResponseUserDTO;
import pt.luis.blogapp.api.dto.UpdateUserDTO;
import pt.luis.blogapp.api.entities.User;
import pt.luis.blogapp.api.infrastructure.security.Password;

public class UserMapper {

    public static User toEntity(CreateUserDTO dto){

        if(dto == null){
            return null;
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(Password.fromPlainText(dto.password()));
        return user;
    }



    public static ResponseUserDTO toResponse(User user){

        if (user == null) {
            return null;
        }

        return new ResponseUserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getLastLoginAt() != null ? user.getLastLoginAt().toString() : "First Login!"
        );
    }

    public static void updateEntity(UpdateUserDTO dto, User user){

        if(dto == null || user == null){
            return;
        }

        if(dto.email() != null){
            user.setEmail(dto.email());
        }
        if(dto.password() != null){
            user.setPassword(Password.fromPlainText(dto.password()));
        }
    }
}
