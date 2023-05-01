# auth-service-without-spring-security
This is an API where authorization and authentication are managed without using Spring Security
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
* Build the project: mvnw clean install
* Run the project: mvnw spring-boot:run 

-> The application will be available at http://localhost:9000.
