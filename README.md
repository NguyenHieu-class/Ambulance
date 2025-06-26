# Ambulance Management Application

This project is a Spring Boot based web application for managing ambulance services. It uses Spring MVC, Spring Data JPA, Thymeleaf and integrates with MySQL for persistent storage.

## Requirements

- **Java** 24 or later
- **Maven** 3.9.x (wrapper included)
- **MySQL** 8

## Database Setup

1. Create a database named `ambulance_management` in your MySQL server:
   ```sql
   CREATE DATABASE ambulance_management;
   ```
2. Update `src/main/resources/application.properties` with your MySQL username and password if they differ from the defaults:
   ```properties
   spring.datasource.username=<YOUR_DB_USER>
   spring.datasource.password=<YOUR_DB_PASSWORD>
   ```

## Building the Project

From the project root, run:

```bash
mvn clean package
```

This command compiles the sources, runs the tests and packages the application.

## Running the Application

Start the application using the Spring Boot Maven plugin:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/`.

### Default Account

When the application starts, a default administrator account is created
automatically:

- **Username**: `admin`
- **Password**: `admin123`

### Accessing the Application

Navigating to `http://localhost:8080/` will redirect to the login page. After a
successful login you will be taken to the dashboard.

### Testing Login

1. Start the application:
   ```bash
   mvn spring-boot:run
   ```
2. Open `http://localhost:8080/` in your browser.
3. Log in with the default credentials `admin` / `admin123`.
4. You should then see the dashboard page.

