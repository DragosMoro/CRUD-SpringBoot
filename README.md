# Flight Management Application

![Flight Management](https://github.com/DragosMoro/CRUD-SpringBoot/blob/main/client/public/demo.png)

A simple CRUD application for flight management with a layered architecture, utilizing Spring Boot for the backend and React for the frontend. This project serves as a basic learning project.

## Features

- **CRUD Operations**: The application allows you to Create, Read, Update, and Delete flight records.

- **Layered Architecture**: The project follows a layered architecture, with a clear separation between the frontend (client) and backend (server) layers.

- **Factory Design Pattern**: Instead of JPA, the backend uses a Factory Design Pattern for managing flight records.

- **SQLite Database**: The SQLite database is located within the "server" directory for data storage.

- **Basic Frontend**: The React frontend provides a straightforward interface for performing CRUD operations and learning purposes.

## Technologies Used

- **Backend (Server)**:
  - Spring Boot: For building robust Java applications.
  - Factory Design Pattern: For flight record management.

- **Frontend (Client)**:
  - React: A popular JavaScript library for building user interfaces.

- **Database**:
  - SQLite: As the database system for data storage, located in the "server" directory.

## Directory Structure

The project has the following directory structure:

- `server`: Contains the Spring Boot backend code and the SQLite database.
- `client`: Contains the React frontend code.

**Thank you for exploring the Flight Management Application!**
