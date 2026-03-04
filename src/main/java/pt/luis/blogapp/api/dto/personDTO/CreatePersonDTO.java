package pt.luis.blogapp.api.dto.personDTO;

import java.time.LocalDate;


public record CreatePersonDTO(

        String firstName,
        String lastName,
        String avatarUrl,
        LocalDate birthDate

) {}
