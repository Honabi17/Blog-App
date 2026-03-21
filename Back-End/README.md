# Blog App API 🚀

This is my final course project for a Blog system API, developed in Java using
the **Spring Boot** framework.
It includes JWT authentication, a role system (USER, MODERATOR, ADMIN), user management, and protected endpoints.


## 🛠️ Technologies used:
- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security + JWT**
- **H2 Database**
- **Maven**
- **ModelMapper**


## 🧱 Project Architecture:

The project follows a modular architecture, organized by responsibilities:

- Controllers - API input layer (REST Controllers).
- DTO - Data transfer objects.
- Models/Entities - JPA entities.
- Repositories - Database access interfaces
- Services - Business rules.
- Infrastructure/Security - Security configurations, JWT, hashing.
- Exceptions - Global error handling.
- Mappers - Conversion between entities and DTO's.

## 📋 Project Structure:

This is an API project for a blog system, developed in **Java** using the 
**Spring Boot** framework.

```
src/main/java/pt/luis/blogapp/api 
├── controllers 
│   ├── personControllers
│   │   └── PersonController.java
│   └── userControllers
│       ├── AdminController.java
│       ├── UserAuthController.java 
│       └── UserController.java 
│
├── dto 
│   ├── personDTO
│   │   ├── CreatePersonDTO.java
│   │   ├── PersonStatsDTO.java
│   │   ├── ProfileDTO.java
│   │   └── UpdatePersonDTO.java
│   └── userDTO
│       ├── AuthResponseDTO.java 
│       ├── CreateUserDTO.java 
│       ├── LoginRequestDTO.java 
│       ├── ResetPasswordConfirmDTO.java
│       ├── ResetPasswordRequestDTO.java
│       ├── ResponseUserDTO.java
│       ├── UpdateEmailDTO.java 
│       ├── UpdatePasswordDTO.java 
│       └── UpdateRoleDTO.java
│
├── exceptions 
│   ├── BadRequestException.java
│   ├── ResourceNotFoundException.java 
│   ├── UserValidationException.java
│   ├── ApiErrorResponse.java 
│   └── GlobalExceptionHandler.java 
│
├── infrastructure 
│   └── securities
│       ├── password
│       │   ├── Password.java
│       │   └── PasswordHasher.java
│       └── security
│           ├── CustomUserDetails.java
│           ├── CustomUserDetailsService.java
│           ├── JwtAuthFilter.java
│           ├── JwtService.java
│           └── SecurityConfig.java 
│
├── mappers 
│   ├── PersonMapper.java
│   └── UserMapper.java 
│
├── models 
│   ├── entities
│   │   └── entity
│   │       ├── BaseEntity.java 
│   │       ├── Category.java 
│   │       ├── Comment.java 
│   │       ├── PasswordResetToken.java
│   │       ├── Person.java 
│   │       ├── PersonStats.java
│   │       ├── Post.java 
│   │       └── User.java 
│   └── role
│       └── UserRole.java
│
├── repositories 
│   ├── personRepositories
│   │   └── PersonRepository.java
│   └── userRepositories
│       ├── TokenRepository.java
│       └── UserRepository.java 
│
└── services 
    ├── personService
    │   ├── PersonService.java
    │   └── PersonServiceImpl.java
    └── userServices
        ├── EmailService.java
        ├── UserAuthService.java 
        ├── UserService.java
        └── serviceImpl
            ├── EmailServiceImpl.java 
            ├── UserAuthServiceImpl.java 
            └── UserServiceImpl.java 

```

## 🔐 Main Features: 
- User registration and authentication with JWT.
- Password recovery via email.
- Role system: USER, MODERATOR, ADMIN.
- User and profile management.
- Management of posts, categories and comments.
- User statistics.
- Global filters and validations.
- Secure password hashing.


## ⚙️ How To Execute:
- 1 - Clones the repository.
- 2 - Open the project in an IDE (IntelliJ or VS Code).
- 3 - Run the `BlogappApplication.java`class.
- 4 - The API will be available at `http://localhost:8081`.
---

Developed By [Luís Santos / Honabi17]

