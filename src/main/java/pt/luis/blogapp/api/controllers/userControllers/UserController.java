package pt.luis.blogapp.api.controllers.userControllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.userDTO.ResponseUserDTO;
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
    public ResponseEntity<ResponseUserDTO> getByUsername(@PathVariable String username){

        ResponseUserDTO dto = userService.getByUsername(username);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseUserDTO> getByEmail(@PathVariable String email){

        ResponseUserDTO dto = userService.getByEmail(email);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<ResponseUserDTO> getByRole(@PathVariable UserRole role){

        ResponseUserDTO dto = userService.getByRole(role);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<ResponseUserDTO> updateEmail(
            @PathVariable String email,
            @RequestBody @Valid UpdateEmailDTO dto
    ){

        ResponseUserDTO updated = userService.updateEmail(email, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/password")
    public ResponseEntity<ResponseUserDTO> updatePassword(
            @RequestBody @Valid UpdatePasswordDTO dto
    ){

        ResponseUserDTO updatedPassword = userService.updatePassword(dto);
        return ResponseEntity.ok(updatedPassword);
    }


}
