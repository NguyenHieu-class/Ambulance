# Ambulance Management

This project is a demo Spring Boot application for managing ambulances, hospitals and related data.

## Seed Database

A sample dataset is provided in [`src/main/resources/data.sql`](src/main/resources/data.sql). Spring Boot loads this script automatically on startup thanks to the property `spring.sql.init.mode=always` in `application.properties`.

The script inserts example records for the main entities such as roles, users, provinces, hospitals, drivers and ambulances. When the application runs with an empty database, these records allow you to explore the APIs and UI immediately.

If you want to reset the sample data, simply clean your database and restart the application.
