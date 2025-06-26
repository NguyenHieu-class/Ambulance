# Ambulance Application

This application manages ambulances, drivers, schedules and related data using Spring Boot. It provides an admin interface for managing records and handling transport requests.

## Prerequisites

- **Java 17+**
- **Maven**
- **MySQL** running with a database named `ambulance_management`

## Database Configuration

Edit `src/main/resources/application.properties` and update the following properties to match your MySQL installation:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ambulance_management?useSSL=false&serverTimezone=UTC
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
```

## Running the Application

1. Ensure the database exists and the above properties are configured.
2. Build and start the server:
   ```bash
   ./mvnw spring-boot:run
   ```
   or use `mvn spring-boot:run` if Maven is installed.
3. Visit `http://localhost:8080/login` to access the login page.

## Admin Login

Create a user with the `ADMIN` role in the `User` table (you can insert it manually or via the admin screens). Log in with that account at `/login`. After authentication you will be redirected to `/admin/dashboard`. The authenticated `User` object is stored in the HTTP session under the key `sessionUser`.

## Uploaded Files

Any files uploaded through the application are stored in the `uploads/` directory in the project root. Make sure this folder exists and is writable by the application.

## Lombok Setup

The project uses [Lombok](https://projectlombok.org/) to reduce boilerplate code. Ensure your IDE has Lombok support enabled so generated getters and other annotations are recognized during development.
