package pt.luis.blogapp.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.AuthResponseDTO;
import pt.luis.blogapp.api.dto.CreateUserDTO;
import pt.luis.blogapp.api.dto.LoginRequestDTO;
import pt.luis.blogapp.api.dto.ResponseUserDTO;
import pt.luis.blogapp.api.services.UserAuthService;


@RestController
@RequestMapping("/api")
public class UserAuthController {

    private final UserAuthService userAuthService;

    public UserAuthController(UserAuthService userAuthService){

        this.userAuthService = userAuthService;
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> created(@Valid @RequestBody CreateUserDTO dto) {

        ResponseUserDTO created = userAuthService.created(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {

        AuthResponseDTO login = userAuthService.login(request);
        return ResponseEntity.ok(login);
    }
}
