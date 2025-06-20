# Ambulance Management

This project is a Spring Boot application for managing ambulance services. It defines domain models for ambulances, hospitals, trips, users, and other related data. JPA annotations are used for persistence and the project contains front‑end assets under `src/main/resources/static`.

## Building with Maven

This project requires **Java 21** or later to compile and run.

The repository includes the Maven wrapper (`mvnw`). Use it to build the project:

```bash
./mvnw clean package
```

During development you can start the application with:

```bash
./mvnw spring-boot:run
```

## Database configuration

Edit `src/main/resources/application.properties` to configure your datasource. Typical settings are:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ambulance_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

The project includes dependencies for MySQL and H2, so you can switch between them by changing these properties.

## Available roles

The system defines three basic user roles:

- **admin** – application administrators
- **medical staff** – doctors or nurses participating in trips
- **driver** – vehicle drivers


## License

This project is licensed under the [MIT License](LICENSE).

