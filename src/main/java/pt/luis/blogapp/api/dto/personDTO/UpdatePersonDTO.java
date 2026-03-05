package pt.luis.blogapp.api.dto.personDTO;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record UpdatePersonDTO(

        Long id,

        @Size(max = 25)
        String firstName,

        @Size(max = 25)
        String lastName,

        @Size(max = 255)
        String avatarUrl,

        LocalDate birthDate
) {}
