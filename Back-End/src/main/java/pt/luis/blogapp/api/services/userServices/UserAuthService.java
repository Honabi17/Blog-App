package pt.luis.blogapp.api.services.userServices;

import pt.luis.blogapp.api.dto.userDTO.*;
import pt.luis.blogapp.api.models.entities.User;

public interface UserAuthService {

    UserResponseDTO created(CreateUserDTO dto);
    AuthResponseDTO login(LoginRequestDTO dto);

    String requestReset(String email);
    String confirmPassword(ResetPasswordConfirmDTO dto);

    User getAuthenticatedUser();

    UserMeDTO getCurrentUser();
}
