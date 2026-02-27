# Blog App API 🚀

This is my final course project for a Blog system API, developed in Java using
the Spring Boot framework.


## 🛠️ Technologies used:
- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **H2 Database**
- **Maven**


## 📋 Project Structure:

This is an API project for a blog system, developed in **Java** using the 
**Spring Boot** framework.

- [x] Initial Spring Boot configuration.
- [x] JPA Entity Mapping(`User`, `Person`, `Category`, `Post`, `Comment`).
- [x] Defining Relationships  `@OneToOne`, `@OneToMany`, `@ManyToOne`.

### 🔗 Data Relationships:
- **@OneToOne:** Each `User` has unique `Profile`, ensuring the separation
of authentication data and personal data.
- **@OneToMany:** A `User` can published multiple `Posts`.
- **@ManyToOne:** Multiple `Posts` can belong to a single `Category`.
- **@OneToMany:** A `Post` can receive multiple `Comments`. 

## ⚙️ How To Execute:
- 1 - Clones the repository.
- 2 - Open the project in an IDE (IntelliJ or VS Code).
- 3 - Run the `BlogappApplication.java`class.
- 4 - The API will be available at `http://localhost:8081`.
---

Developed By [Luís Santos / Honabi17]

