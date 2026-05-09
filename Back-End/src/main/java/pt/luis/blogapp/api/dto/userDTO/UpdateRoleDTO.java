package pt.luis.blogapp.api.dto.userDTO;

import pt.luis.blogapp.api.models.role.UserRole;

public record UpdateRoleDTO(
        String username,
        UserRole newRole
) {
}
