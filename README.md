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


## 📋 Project Structure:

This is an API project for a blog system, developed in **Java** using the 
**Spring Boot** framework.

```
src/main/java/pt/luis/blogapp/api 
├── controllers 
│ ├── AdminController.java
│ ├── UserAuthController.java 
│ └── UserController.java 
├── dto 
│ ├── AuthResponseDTO.java 
│ ├── CreateUserDTO.java 
│ ├── LoginRequestDTO.java 
│ ├── ResponseUserDTO.java
│ ├── UpdateEmailDTO.java 
│ └── UpdatePasswordDTO.java 
├── entities 
│ ├── entity 
│ │ └── BaseEntity.java 
│ ├── role 
│ │ └── UserRole.java 
│ ├── Category.java 
│ ├── Comment.java 
│ ├── PasswordResetToken.java
│ ├── Person.java 
│ ├── Post.java 
│ └── User.java 
├── exceptions 
│ ├── ApiErrorResponse.java 
│ ├── GlobalExceptionHandler.java 
│ ├── ResourceNotFoundException.java 
│ └── UserValidationException.java 
├── infrastructure 
│ └── security 
│ │  ├── CustomUserDetails.java
│ │  ├── CustomUserDetailsService.java
│ │  ├── JwtAuthFilter.java
│ │  ├── JwtService.java
│ │  ├── Password.java 
│ │  ├── PasswordHasher.java
│ │  ├── ResetPasswordConfirmDTO.java
│ │  ├── ResetPasswordRequestDTO.java 
│ │  └── SecurityConfig.java 
├── mappers 
│ └── UserMapper.java 
├── repositories 
│ ├── TokenRepository.java
│ └── UserRepository.java 
└── services 
├── serviceImpl
│ ├── EmailServiceImpl.java 
│ ├── UserAuthServiceImpl.java 
│ └── UserServiceImpl.java 
├── EmailService.java
├── UserAuthService.java 
└── UserService.java
```

## ⚙️ How To Execute:
- 1 - Clones the repository.
- 2 - Open the project in an IDE (IntelliJ or VS Code).
- 3 - Run the `BlogappApplication.java`class.
- 4 - The API will be available at `http://localhost:8081`.
---

Developed By [Luís Santos / Honabi17]

