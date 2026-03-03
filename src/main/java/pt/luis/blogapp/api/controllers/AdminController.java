package pt.luis.blogapp.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.luis.blogapp.api.dto.ResponseUserDTO;
import pt.luis.blogapp.api.entities.role.UpdateRoleDTO;
import pt.luis.blogapp.api.services.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService){

        this.userService = userService;
    }

    @PatchMapping("/promote")
    public ResponseUserDTO promote(@RequestBody UpdateRoleDTO dto){

        return userService.updateRole(dto);
    }
}
