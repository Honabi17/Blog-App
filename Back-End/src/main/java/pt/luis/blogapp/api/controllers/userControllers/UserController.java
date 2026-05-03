package pt.luis.blogapp.api.controllers.userControllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.userDTO.UserResponseDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdateEmailDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdatePasswordDTO;
import pt.luis.blogapp.api.models.role.UserRole;
import pt.luis.blogapp.api.services.userServices.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController{


    private UserService userService;

    public UserController(UserService userService){

        this.userService = userService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getByUsername(@PathVariable String username){

        UserResponseDTO dto = userService.getByUsername(username);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/email")
    public ResponseEntity<UserResponseDTO> updateEmail(@PathVariable @Valid UpdateEmailDTO dto){

        UserResponseDTO update = userService.updateEmail(dto);
        return ResponseEntity.ok(update);
    }

    @PutMapping("/password")
    public ResponseEntity<UserResponseDTO> updatePassword(
            @RequestBody @Valid UpdatePasswordDTO dto
    ){

        UserResponseDTO updatedPassword = userService.updatePassword(dto);
        return ResponseEntity.ok(updatedPassword);
    }


}
