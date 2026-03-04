package pt.luis.blogapp.api.services.personService;

import pt.luis.blogapp.api.dto.personDTO.CreatePersonDTO;
import pt.luis.blogapp.api.dto.personDTO.ProfileDTO;
import pt.luis.blogapp.api.dto.personDTO.UpdatePersonDTO;


public interface PersonService {

    ProfileDTO created(CreatePersonDTO dto);

    ProfileDTO getProfile();

    ProfileDTO updated(UpdatePersonDTO dto);

    void deleteProfile();
}
