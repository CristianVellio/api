# 🩺 Voll Med Clinic API

## Overview
Voll Med Clinic API is a RESTful service built with Spring Boot for managing clinic operations, including authentication, user management, and medical data handling. It uses JWT authentication for security and integrates with MySQL for persistent data storage.

## Features
- User authentication and JWT-based authorization
- Role-based access control with Spring Security
- MySQL database integration
- Data validation with Hibernate Validator
- Database migration with Flyway
- API development with Spring Boot
- RESTful architecture

## Technologies Used
- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Flyway** (Database Migrations)
- **MySQL** (Database)
- **Hibernate (JPA)**
- **Maven**

## Installation and Setup
### Prerequisites
Ensure you have the following installed on your system:
- Java 21
- Maven
- MySQL

### Clone the Repository
```sh
git clone https://github.com/your-repository/voll-med-api.git
cd voll-med-api
```

### Configure Database
Create a MySQL database and update your `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/voll_med_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### Run the Application
```sh
mvn spring-boot:run
```

## API Endpoints

### Authentication
#### Generate Token (Login)
**POST** `/auth/login`
```json
{
  "username": "user@example.com",
  "password": "password"
}
```
**Response:**
```json
{
  "token": "eyJhbGciOiJI..."
}
```

### User Management
#### Get User Details
**GET** `/users/{id}`

#### Register a New User
**POST** `/users/register`
```json
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "securepassword"
}
```

### Secured Endpoints
JWT Token is required for accessing protected resources.
Send token in Authorization header:
```sh
Authorization: Bearer <your_token>
```

## Security
- **JWT-based Authentication**: Secure endpoints using JSON Web Tokens.
- **Password Encryption**: Uses bcrypt hashing for password storage.
- **Role-based Access Control**: Restricts user access based on roles.

## Testing
To run tests, execute:
```sh
mvn test
```
