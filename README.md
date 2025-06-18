# Ambulance Management

This project is a simplified Spring Boot application for managing ambulances and staff. It exposes a basic web interface for administrators. The application uses a MySQL database.

## Setup

1. **Install Prerequisites**
   - Java 17
   - Maven 3
   - A running MySQL instance
2. **Clone the Repository**
   ```bash
   git clone <repo>
   cd Ambulance
   ```
3. **Configure the Database**
   Edit `src/main/resources/application.properties` or set the following environment variables:
   - `SPRING_DATASOURCE_URL` – JDBC URL for your MySQL instance
   - `SPRING_DATASOURCE_USERNAME` – database username
   - `SPRING_DATASOURCE_PASSWORD` – database password

   The default configuration points to a local MySQL database named `ambulance_db`.
4. **Build the Project**
   ```bash
   ./mvnw clean package
   ```

## Running the Application

Run the Spring Boot application using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

Alternatively, run the packaged JAR:

```bash
java -jar target/CarRental2-0.0.1-SNAPSHOT.jar
```

## Database Notes

Ensure your MySQL instance contains the `ambulance_db` database. You can create the schema using the SQL file at `src/main/resources/db/init.sql`.

## License

This project is provided as-is for demonstration purposes.
