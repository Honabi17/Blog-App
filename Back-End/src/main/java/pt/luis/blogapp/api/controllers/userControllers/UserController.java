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
@RequestMapping("/api/blog ")
public class UserController{


    private UserService userService;

    public UserController(UserService userService){

        this.userService = userService;
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getByUsername(@PathVariable String username){

        UserResponseDTO dto = userService.getByUsername(username);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getByEmail(@PathVariable String email){

        UserResponseDTO dto = userService.getByEmail(email);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<UserResponseDTO> getByRole(@PathVariable UserRole role){

        UserResponseDTO dto = userService.getByRole(role);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> updateEmail(
            @PathVariable String email,
            @RequestBody @Valid UpdateEmailDTO dto
    ){

        UserResponseDTO updated = userService.updateEmail(email, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/password")
    public ResponseEntity<UserResponseDTO> updatePassword(
            @RequestBody @Valid UpdatePasswordDTO dto
    ){

        UserResponseDTO updatedPassword = userService.updatePassword(dto);
        return ResponseEntity.ok(updatedPassword);
    }


}
