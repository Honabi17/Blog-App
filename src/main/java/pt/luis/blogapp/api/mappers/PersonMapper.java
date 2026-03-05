package pt.luis.blogapp.api.mappers;


import pt.luis.blogapp.api.dto.personDTO.CreatePersonDTO;
import pt.luis.blogapp.api.dto.personDTO.PersonStatsDTO;
import pt.luis.blogapp.api.dto.personDTO.ProfileDTO;
import pt.luis.blogapp.api.dto.personDTO.UpdatePersonDTO;
import pt.luis.blogapp.api.models.entities.Person;
import pt.luis.blogapp.api.models.entities.PersonStats;


public class PersonMapper {

    public static ProfileDTO toProfileDTO(Person person){

        return new ProfileDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAvatarUrl(),
                person.getBirthDate()
        );
    }

    public static PersonStatsDTO toStatsDTO(PersonStats stats){

        return new PersonStatsDTO(
                stats.getCategoriesCount(),
                stats.getPostsCount(),
                stats.getCommentsCount()
        );
    }

    public static Person toPersonEntity(CreatePersonDTO dto){

        Person person = new Person();
        person.setFirstName(dto.firstName());
        person.setLastName(dto.lastName());
        person.setAvatarUrl(dto.avatarUrl());
        person.setBirthDate(dto.birthDate());
        return person;
    }

    public static void updatePersonEntity(Person person, UpdatePersonDTO dto){

        if(dto.firstName() != null && dto.firstName().isBlank()){
            person.setFirstName(dto.firstName());
        }

        if(dto.lastName() != null && dto.lastName().isBlank()){
            person.setLastName(dto.lastName());
        }

        if(dto.avatarUrl() != null && dto.avatarUrl().isBlank()){
            person.setAvatarUrl(dto.avatarUrl());
        }

        if(dto.birthDate() != null){
            person.setBirthDate(dto.birthDate());
        }

    }
}
