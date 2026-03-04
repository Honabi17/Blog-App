package pt.luis.blogapp.api.dto.personDTO;

import java.time.LocalDate;


public record UpdatePersonDTO(
        Long id,
        String firstName,
        String lastName,
        String avatarUrl,
        LocalDate birthDate
) { }
