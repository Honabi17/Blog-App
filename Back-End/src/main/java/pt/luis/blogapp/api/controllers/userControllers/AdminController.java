package pt.luis.blogapp.api.controllers.userControllers;


import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.luis.blogapp.api.dto.userDTO.UserResponseDTO;
import pt.luis.blogapp.api.dto.userDTO.UpdateRoleDTO;
import pt.luis.blogapp.api.services.userServices.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService){

        this.userService = userService;
    }

    @PatchMapping("/promote")
    public UserResponseDTO promote(@RequestBody UpdateRoleDTO dto){

        return userService.updateRole(dto);
    }
}
