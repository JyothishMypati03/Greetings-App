# Greetings App

A Java web application for creating, retrieving, listing, updating, and deleting greetings using Java Servlets, Plain JDBC, PostgreSQL, Maven, Jackson, Apache Tomcat, and Postman.

## Use Cases

| ID    | Use Case           | Endpoint          | Method |
| ----- | ------------------ | ----------------- | ------ |
| UC-01 | Create Greeting    | `/greetings`      | POST   |
| UC-02 | Get Greeting By ID | `/greetings/{id}` | GET    |
| UC-03 | Get All Greetings  | `/greetings`      | GET    |
| UC-04 | Update Greeting    | `/greetings/{id}` | PUT    |
| UC-05 | Delete Greeting    | `/greetings/{id}` | DELETE |

## Technologies Used

* Java 21
* Java Servlet
* Apache Tomcat
* Maven
* Plain JDBC
* PostgreSQL
* Jackson
* Postman

## Project Flow

```text
Postman
   ↓
GreetingServlet
   ↓
GreetingService
   ↓
GreetingDAO
   ↓
GreetingDAOImpl
   ↓
DBConnection
   ↓
Plain JDBC
   ↓
PostgreSQL
```

## Project Structure

```text
Greetings-App
├── pom.xml
├── .gitignore
├── README.md
│
└── src
    └── main
        ├── java
        │   └── com.bridgelabz.greetingapp
        │       ├── controller
        │       │   └── GreetingServlet.java
        │       ├── dao
        │       │   ├── GreetingDAO.java
        │       │   └── GreetingDAOImpl.java
        │       ├── model
        │       │   └── Greeting.java
        │       ├── service
        │       │   └── GreetingService.java
        │       └── util
        │           └── DBConnection.java
        │
        └── resources
            ├── db.properties
            └── db.properties.example
```

## Database

### Database Name

```text
greetings_app
```

### Create Database

```sql
CREATE DATABASE greetings_app;
```

### Create Table

```sql
CREATE TABLE greetings (
    greeting_id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    greeting_message TEXT NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

## Database Configuration

Create:

```text
src/main/resources/db.properties
```

Add:

```properties
db.url=jdbc:postgresql://localhost:5432/greetings_app
db.username=postgres
db.password=YOUR_PASSWORD
```

Do not push `db.properties` to GitHub.

Use `db.properties.example` for sharing the configuration format.

## UC-01: Create Greeting

### Endpoint

```text
POST /greetings
```

### Request

```json
{
  "userName": "Jyothish",
  "greetingMessage": "Hello, welcome to My Greetings App!"
}
```

### Response

```json
{
  "greetingId": 1,
  "userName": "Jyothish",
  "greetingMessage": "Hello, welcome to My Greetings App!",
  "createdDate": "2026-08-11T15:50:30"
}
```

## UC-02: Get Greeting By ID

### Endpoint

```text
GET /greetings/{id}
```

### Example

```text
GET http://localhost:8080/greetings/1
```

### Response

```json
{
  "greetingId": 1,
  "userName": "Jyothish",
  "greetingMessage": "Hello, welcome to My Greetings App!",
  "createdDate": "2026-08-11T15:50:30"
}
```

## UC-03: Get All Greetings

### Endpoint

```text
GET /greetings
```

### Example

```text
GET http://localhost:8080/greetings
```

### Response

```json
[
  {
    "greetingId": 1,
    "userName": "Jyothish",
    "greetingMessage": "Hello!",
    "createdDate": "2026-08-11T15:30:00"
  },
  {
    "greetingId": 2,
    "userName": "Rahul",
    "greetingMessage": "Welcome!",
    "createdDate": "2026-08-11T16:00:00"
  }
]
```

## UC-04: Update Greeting

### Endpoint

```text
PUT /greetings/{id}
```

### Example

```text
PUT http://localhost:8080/greetings/1
```

### Request

```json
{
  "userName": "Jyothish Mypati",
  "greetingMessage": "Updated greeting message!"
}
```

### Response

```json
{
  "message": "Greeting updated successfully"
}
```

## UC-05: Delete Greeting

### Endpoint

```text
DELETE /greetings/{id}
```

### Example

```text
DELETE http://localhost:8080/greetings/1
```

No request body is required.

### Response

```json
{
  "message": "Greeting deleted successfully"
}
```

If the greeting does not exist:

```json
{
  "error": "Greeting not found"
}
```

## Architecture

### Controller

`GreetingServlet`

Handles HTTP requests and responses.

### Model

`Greeting`

Represents greeting data.

### Service

`GreetingService`

Contains business logic and validation.

### DAO

`GreetingDAO`

Defines database operations.

`GreetingDAOImpl` implements database operations using Plain JDBC.

### Database Connection

`DBConnection`

Creates the PostgreSQL database connection using JDBC.

## How to Run

### 1. Create Database

```sql
CREATE DATABASE greetings_app;
```

### 2. Create Table

Run the `greetings` table SQL given above.

### 3. Configure Database

Create `db.properties` and add your PostgreSQL credentials.

### 4. Build Project

```bash
mvn clean package
```

### 5. Run with Tomcat

Deploy the generated WAR file to Apache Tomcat and start the server.

### 6. Test with Postman

```text
UC-01
POST http://localhost:8080/greetings

UC-02
GET http://localhost:8080/greetings/1

UC-03
GET http://localhost:8080/greetings

UC-04
PUT http://localhost:8080/greetings/1

UC-05
DELETE http://localhost:8080/greetings/1
```

## Use Case Status

```text
UC-01: Create Greeting       COMPLETED
UC-02: Get Greeting By ID    COMPLETED
UC-03: Get All Greetings     COMPLETED
UC-04: Update Greeting       COMPLETED
UC-05: Delete Greeting       COMPLETED
```

## Author

**Jyothish Mypati**

Java Developer | Java Full Stack Developer
