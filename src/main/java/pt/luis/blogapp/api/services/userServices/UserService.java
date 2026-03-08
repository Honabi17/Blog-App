package pt.luis.blogapp.api.services.userServices;

import pt.luis.blogapp.api.dto.userDTO.UserResponseDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdateEmailDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdatePasswordDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdateRoleDTO;
import pt.luis.blogapp.api.models.role.UserRole;

public interface UserService {

    UserResponseDTO getByUsername(String username);
    UserResponseDTO getByEmail(String email);
    UserResponseDTO getByRole(UserRole role);

    UserResponseDTO updateEmail(String currentEmail, UpdateEmailDTO dto);
    UserResponseDTO updatePassword(UpdatePasswordDTO dto);

    UserResponseDTO updateRole(UpdateRoleDTO dto);
}
