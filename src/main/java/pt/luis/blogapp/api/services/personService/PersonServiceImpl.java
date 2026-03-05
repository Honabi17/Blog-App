package pt.luis.blogapp.api.services.personService;


import jakarta.transaction.Transactional;
import pt.luis.blogapp.api.exceptions.Exceptions.BadRequestException;
import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.dto.personDTO.CreatePersonDTO;
import pt.luis.blogapp.api.dto.personDTO.ProfileDTO;
import pt.luis.blogapp.api.dto.personDTO.UpdatePersonDTO;
import pt.luis.blogapp.api.exceptions.Exceptions.ResourceNotFoundException;
import pt.luis.blogapp.api.mappers.PersonMapper;
import pt.luis.blogapp.api.models.entities.Person;
import pt.luis.blogapp.api.models.entities.PersonStats;
import pt.luis.blogapp.api.models.entities.User;
import pt.luis.blogapp.api.repositories.PersonRepository;
import pt.luis.blogapp.api.repositories.userRepositories.UserRepository;
import pt.luis.blogapp.api.services.userServices.UserAuthService;


@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;
    private UserAuthService authService;
    private UserRepository userRepository;

    public PersonServiceImpl(
            PersonRepository personRepository,
            UserAuthService authService,
            UserRepository userRepository
    ){
        this.personRepository = personRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }


    private User currentUser(){
        return authService.getAuthenticatedUser();
    }

    private Person findPerson(){
        User user = currentUser();
        return personRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }


    @Override
    @Transactional
    public ProfileDTO created(CreatePersonDTO dto) {

        User user = currentUser();

        if(personRepository.existsByUser(user)){
            throw  new BadRequestException("Profile already exists!");
        }

        Person person = PersonMapper.toPersonEntity(dto);
        person.setUser(user);

        PersonStats stats = new PersonStats();
        stats.setPerson(person);
        stats.setCategoriesCount(0);
        stats.setPostsCount(0);
        stats.setCommentsCount(0);

        person.setStats(stats);

        personRepository.save(person);

        return PersonMapper.toProfileDTO(person);
    }

    @Override
    public ProfileDTO getProfile() {

        Person person = findPerson();

        return PersonMapper.toProfileDTO(person);
    }

    @Override
    public ProfileDTO updated(UpdatePersonDTO dto) {

        Person person = findPerson();

        PersonMapper.updatePersonEntity(person, dto);

        personRepository.save(person);

        return PersonMapper.toProfileDTO(person);
    }

    @Override
    public void deleteProfile() {

        Person person = findPerson();

        if(person.getStats() != null){
            person.getStats().setPerson(null);
            person.setStats(null);
        }

        User user = person.getUser();
        user.setPerson(null);

        personRepository.delete(person);
    }
}