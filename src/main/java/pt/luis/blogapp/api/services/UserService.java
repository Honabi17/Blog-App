package pt.luis.blogapp.api.services;

import pt.luis.blogapp.api.dto.ResponseUserDTO;
import pt.luis.blogapp.api.dto.UpdateEmailDTO;
import pt.luis.blogapp.api.dto.UpdatePasswordDTO;
import pt.luis.blogapp.api.entities.role.UpdateRoleDTO;
import pt.luis.blogapp.api.entities.role.UserRole;

public interface UserService {

    ResponseUserDTO getByUsername(String username);
    ResponseUserDTO getByEmail(String email);
    ResponseUserDTO getByRole(UserRole role);

    ResponseUserDTO updateEmail(String currentEmail, UpdateEmailDTO dto);
    ResponseUserDTO updatePassword(UpdatePasswordDTO dto);

    ResponseUserDTO updateRole(UpdateRoleDTO dto);
}
