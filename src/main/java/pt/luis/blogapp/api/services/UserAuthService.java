package pt.luis.blogapp.api.services;

import pt.luis.blogapp.api.dto.AuthResponseDTO;
import pt.luis.blogapp.api.dto.CreateUserDTO;
import pt.luis.blogapp.api.dto.LoginRequestDTO;
import pt.luis.blogapp.api.dto.ResponseUserDTO;
import pt.luis.blogapp.api.infrastructure.security.ResetPasswordConfirmDTO;

public interface UserAuthService {

    ResponseUserDTO created(CreateUserDTO dto);
    AuthResponseDTO login(LoginRequestDTO dto);

    String requestReset(String email);
    String confirmPassword(ResetPasswordConfirmDTO dto);
}
