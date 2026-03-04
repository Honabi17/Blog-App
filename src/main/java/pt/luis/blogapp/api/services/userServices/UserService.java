package pt.luis.blogapp.api.services.userServices;

import pt.luis.blogapp.api.dto.userDTO.ResponseUserDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdateEmailDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdatePasswordDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdateRoleDTO;
import pt.luis.blogapp.api.models.role.UserRole;

public interface UserService {

    ResponseUserDTO getByUsername(String username);
    ResponseUserDTO getByEmail(String email);
    ResponseUserDTO getByRole(UserRole role);

    ResponseUserDTO updateEmail(String currentEmail, UpdateEmailDTO dto);
    ResponseUserDTO updatePassword(UpdatePasswordDTO dto);

    ResponseUserDTO updateRole(UpdateRoleDTO dto);
}
