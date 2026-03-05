package pt.luis.blogapp.api.controllers.personControllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.luis.blogapp.api.dto.personDTO.CreatePersonDTO;
import pt.luis.blogapp.api.dto.personDTO.ProfileDTO;
import pt.luis.blogapp.api.dto.personDTO.UpdatePersonDTO;
import pt.luis.blogapp.api.services.personService.PersonService;

@RestController
@RequestMapping("/api/user")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody CreatePersonDTO dto){

        ProfileDTO profile = personService.created(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(){

        ProfileDTO profile = personService.getProfile();

        return ResponseEntity.ok(profile);
    }

    @PatchMapping("/profile")
    public ResponseEntity<ProfileDTO> updateProfile(@Valid @RequestBody UpdatePersonDTO dto){

        ProfileDTO update = personService.updated(dto);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> deleteProfile(){

        personService.deleteProfile();

        return ResponseEntity.noContent().build();
    }

}
