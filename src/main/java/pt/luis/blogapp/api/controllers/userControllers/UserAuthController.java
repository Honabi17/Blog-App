package pt.luis.blogapp.api.controllers.userControllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.userDTO.AuthResponseDTO;
import pt.luis.blogapp.api.dto.userDTO.CreateUserDTO;
import pt.luis.blogapp.api.dto.userDTO.LoginRequestDTO;
import pt.luis.blogapp.api.dto.userDTO.UserResponseDTO;
import pt.luis.blogapp.api.dto.userDTO.ResetPasswordConfirmDTO;
import pt.luis.blogapp.api.dto.userDTO.ResetPasswordRequestDTO;
import pt.luis.blogapp.api.services.userServices.UserAuthService;


@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private final UserAuthService authService;

    public UserAuthController(UserAuthService userAuthService){

        this.authService = userAuthService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> created(@Valid @RequestBody CreateUserDTO dto) {

        UserResponseDTO created = authService.created(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {

        AuthResponseDTO login = authService.login(request);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/reset-password/request")
    public ResponseEntity<String> requestReset(@RequestBody ResetPasswordRequestDTO dto){

        authService.requestReset(dto.email());
        return ResponseEntity.ok("Reset email send");
    }

    @PostMapping("/reset-password/confirm")
    public ResponseEntity<String> confirmReset(@RequestBody ResetPasswordConfirmDTO dto){

        authService.confirmPassword(dto);
        return ResponseEntity.ok("Password updated successfully");
    }
}
