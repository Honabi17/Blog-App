package pt.luis.blogapp.api.dto.personDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record CreatePersonDTO(

        @NotBlank
        @Size(max = 25)
        String firstName,

        @NotBlank
        @Size(max = 25)
        String lastName,

        @Size(max = 255)
        String avatarUrl,

        LocalDate birthDate
) {}
