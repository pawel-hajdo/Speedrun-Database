# Speedrun-Database Spring Boot API
This project is an API server built with Spring Boot. It can be used as an base to web application similar to https://www.speedrun.com/ </br>
Endpoints documentation -> [Endpoints.md](https://github.com/pawel-hajdo/Speedrun-Database/blob/main/server/endpoints.md)

## Features
- User registration and login with JWT authentication
- Fetching information about users, games, platforms and runs from database
- Adding new users, platforms, games and runs to database
- Following other users, rating games, assigning platforms to the game
- Customized access denied handling
- Password encryption using BCrypt
- Email notifications /*TODO*/

## Technologies
- Spring Boot 3.2.1
- Spring Security
- JSON Web Tokens (JWT)
- Postgresql
- BCrypt
- Maven

## Getting started
To get started with this project, you will need to have the following installed on your local machine:
- JDK 17+
- Maven 3+

To build and run the project, follow these steps:
- Clone the repository: `git clone https://github.com/pawel-hajdo/Speedrun-Database.git`
- Add environmental variables:
  * **DB_URL** - url link to your database 
  * **DB_USERNAME** - your database username
  * **DB_PASSWORD** - password to your database
  * **ENCRYPTION_KEY** - your generated encryption key
- Build the project
- Run the project

-> The application will be available at http://localhost:8080.
