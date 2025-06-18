# Ambulance Car Rental

This project is a Spring Boot application that provides a car rental service. It exposes a web interface and REST endpoints for managing cars, bookings and payments. The application uses a Microsoft SQL Server database and includes a basic VNPay payment integration.

## Setup

1. **Install Prerequisites**
   - Java 17
   - Maven 3
   - A running SQL Server instance
2. **Clone the Repository**
   ```bash
   git clone <repo>
   cd Ambulance
   ```
3. **Configure the Database**
   Edit `src/main/resources/application.properties` or set the following environment variables:
   - `SPRING_DATASOURCE_URL` – JDBC URL for your SQL Server
   - `SPRING_DATASOURCE_USERNAME` – database username
   - `SPRING_DATASOURCE_PASSWORD` – database password

   The default configuration in `application.properties` points to a local SQL Server database named `CarRental` listening on port `59656`.

4. **Build the Project**
   ```bash
   ./mvnw clean package
   ```

## Running the Application

Run the Spring Boot application using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The application will start on port `8083` by default. You can override the port by setting the `SERVER_PORT` environment variable.

Alternatively, run the packaged JAR:

```bash
java -jar target/CarRental2-0.0.1-SNAPSHOT.jar
```

## Environment Variables

Besides the datasource properties mentioned above, you may provide additional configuration through the following variables:

- `SERVER_PORT` – HTTP port for the Spring Boot server (defaults to `8083`).
- `JAVA_TOOL_OPTIONS` – optional JVM flags, useful when deploying to platforms like Heroku.

## Database Notes

Ensure your SQL Server instance contains the `CarRental` database (or the name you configured). The application will create and update tables automatically using Hibernate when it starts.

## License

This project is provided as-is for demonstration purposes.

