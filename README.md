# Ambulance Application

This project manages ambulances and related data using Spring Boot.

## Login

1. Start the application with your preferred Spring Boot tool or `mvn spring-boot:run`.
2. Visit `http://localhost:8080/login` in your browser.
3. Enter your username and password.
4. After successful authentication you will be redirected to the admin dashboard at `/admin/dashboard`.

The authenticated `User` object is stored in the HTTP session under the key `"sesionUser"`.
