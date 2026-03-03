package pt.luis.blogapp.api.entities.role;

public record UpdateRoleDTO(
        long userId,
        UserRole newRole
) {
}
