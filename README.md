

# Crop Monitoring System - Backend

## Description
The **Crop Monitoring System - Backend** is a Spring Boot-based application that serves as the backend for a crop monitoring system. This system is designed to manage various operations related to crop monitoring, including tracking vehicle status, managing equipment, logging monitoring data, and handling user authentication.

## Features
- User authentication and authorization (using JWT).
- CRUD operations for managing vehicles, equipment, and monitoring logs.
- Custom exceptions for error handling.
- Integration with the frontend for real-time crop data monitoring.
- REST API for easy integration with the frontend.

## Technologies Used
- **Java**: Programming language used for the backend.
- **Spring Boot**: Framework used to develop the backend.
- **Spring Security**: For user authentication and authorization.
- **JPA/Hibernate**: For database management.
- **PostgreSQL/MySQL**: Relational database for storing application data.
- **ModelMapper**: For entity-DTO conversion.
- **JWT**: For token-based authentication.

## Setup Instructions

### Prerequisites
Make sure you have the following tools installed:
- Java 17 or above
- Maven
- PostgreSQL or MySQL database
- IntelliJ IDEA or your preferred IDE

### Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/KavinduSenesh/Crop-Monitoring-System-Backend.git
   cd Crop-Monitoring-System-Backend
Set up the database:

Create a new PostgreSQL/MySQL database.

Configure your database connection in src/main/resources/application.properties:

properties
Copy
Edit
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/crop_monitoring
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```
Or, if you're using MySQL, update the dialect and URL accordingly.

Build and run the application:

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
The backend will be running on http://localhost:8080.

Endpoints - 
Here are the available endpoints in the API:
```
```
Authentication
```
POST /api/auth/login: Login using email and password to get a JWT token.

POST /api/auth/register: Register a new user.
```
Vehicles
```
GET /api/vehicles: Get a list of all vehicles.

GET /api/vehicles/{id}: Get details of a vehicle by its ID.

POST /api/vehicles: Add a new vehicle.

PUT /api/vehicles/{id}: Update a vehicle by its ID.

DELETE /api/vehicles/{id}: Delete a vehicle by its ID.
```
Equipment
```
GET /api/equipment: Get a list of all equipment.

GET /api/equipment/{id}: Get details of equipment by its ID.

POST /api/equipment: Add new equipment.

PUT /api/equipment/{id}: Update equipment by its ID.

DELETE /api/equipment/{id}: Delete equipment by its ID.
```
Monitoring Logs
```
GET /api/logs: Get all monitoring logs.

POST /api/logs: Create a new monitoring log.
```
Contributing
```
Feel free to fork the repository, create a branch for your feature, and submit a pull request. Please ensure that your code follows the existing style and that you have written tests to verify your changes.
```
License
This project is licensed under the MIT License - see the LICENSE file for details.
