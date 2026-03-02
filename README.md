# Blog App API 🚀

This is my final course project for a Blog system API, developed in Java using
the Spring Boot framework.


## 🛠️ Technologies used:
- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Maven**


## 📋 Project Structure:

This is an API project for a blog system, developed in **Java** using the 
**Spring Boot** framework.

```
src/main/java/pt/luis/blogapp/api 
├── controllers 
│ ├── UserAuthController.java 
│ └── UserController.java 
├── dto 
│ ├── AuthResponseDTO.java 
│ ├── CreateUserDTO.java 
│ ├── LoginRequestDTO.java 
│ ├── ResponseUserDTO.java 
│ └── UpdateUserDTO.java 
├── entities 
│ ├── entity 
│ │ └── BaseEntity.java 
│ ├── role 
│ │ └── UserRole.java 
│ ├── Category.java 
│ ├── Comment.java 
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
│ ├── Password.java 
│ ├── PasswordHasher.java 
│ └── SecurityConfig.java 
├── mappers 
│ └── UserMapper.java 
├── repositories 
│ └── UserRepository.java 
└── services 
├── serviceImpl 
│ ├── UserAuthServiceImpl.java 
│ └── UserServiceImpl.java 
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

