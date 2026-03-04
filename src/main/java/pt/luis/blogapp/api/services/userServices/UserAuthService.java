package pt.luis.blogapp.api.services.userServices;

import pt.luis.blogapp.api.dto.userDTO.AuthResponseDTO;
import pt.luis.blogapp.api.dto.userDTO.CreateUserDTO;
import pt.luis.blogapp.api.dto.userDTO.LoginRequestDTO;
import pt.luis.blogapp.api.dto.userDTO.ResponseUserDTO;
import pt.luis.blogapp.api.dto.userDTO.ResetPasswordConfirmDTO;

public interface UserAuthService {

    ResponseUserDTO created(CreateUserDTO dto);
    AuthResponseDTO login(LoginRequestDTO dto);

    String requestReset(String email);
    String confirmPassword(ResetPasswordConfirmDTO dto);
}
