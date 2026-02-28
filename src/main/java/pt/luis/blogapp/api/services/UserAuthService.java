package pt.luis.blogapp.api.services;

import pt.luis.blogapp.api.dto.AuthResponseDTO;
import pt.luis.blogapp.api.dto.CreateUserDTO;
import pt.luis.blogapp.api.dto.LoginRequestDTO;
import pt.luis.blogapp.api.dto.ResponseUserDTO;

public interface UserAuthService {

    ResponseUserDTO created(CreateUserDTO dto);
    AuthResponseDTO login(LoginRequestDTO dto);
}
