# auth-service-without-spring-security
This is an Spring boot rest API where authorization and authentication are managed without using Spring Security
## Features
* User registration and login 
* Password encryption using Argon2
* Role-based authorization
* Customized access denied handling

## Technologies
* Spring Boot 3.0
* Argon2
* Maven
* H2 database
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 19+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/AuxBasilias/auth-service-without-spring-security`
* Navigate to the project directory: cd auth-service-without-spring-security
* Build the project: `mvnw clean install`
* Run the project: `mvnw spring-boot:run`

-> The application will be available at http://localhost:9000.

## Endpoints

| Method | URL | Description | valid request body|
|--------|-----|-------------|------------------------------|
| POST | /signup | Sign up | [JSON](#signup)  |
| POST | /login | Sign in | [JSON](#login)  |
| GET | /comfirm | Shows that you are logged in or not |  |
| GET | /authenrization | Shows that you are admin or user |  |
| GET | /authenrization/admin | Only authorized for admin |  |
| GET | /authenrization/user | Only authorized for user |  |
| GET | /who_is_connected | Shows who is connected |  |
| GET | /logout | Only authorized for admin | To log out |

## Example of valid request body
##### <a id="signup">Sign up -> http://localhost:9000/signup</a>
```json
{
    "username":"user1",
    "email":"user2@gmail.com",
    "password":"uarenot",
    "role":"USER"
}
```

##### <a id="login">Log in -> http://localhost:9000/login</a>
```json
{
    "usernameOremail":"user2@gmail.com",
    "password":"uarenot"
}
```


