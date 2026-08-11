# Greetings App

A Java web application for creating and retrieving greetings using Java Servlets, Plain JDBC, PostgreSQL, Maven, Jackson, Apache Tomcat, and Postman.

## UC-01: Create Greeting

Creates a new greeting and stores it in PostgreSQL.

**Endpoint**

```text
POST /greetings
```

**Request Body**

```json
{
  "userName": "Jyothish",
  "greetingMessage": "Hello, welcome to My Greetings App!"
}
```

## UC-02: Get Greeting By ID

Retrieves a greeting from PostgreSQL using its ID.

**Endpoint**

```text
GET /greetings/{id}
```

**Example**

```text
GET http://localhost:8080/greetings/1
```

**Response**

```json
{
  "greetingId": 1,
  "userName": "Jyothish",
  "greetingMessage": "Hello, welcome to My Greetings App!",
  "createdDate": "2026-08-11T17:00:00"
}
```

If the greeting does not exist:

```json
{
  "error": "Greeting not found"
}
```

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

### Table

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

## API Endpoints

| ID    | Use Case           | Endpoint          | Method |
| ----- | ------------------ | ----------------- | ------ |
| UC-01 | Create Greeting    | `/greetings`      | POST   |
| UC-02 | Get Greeting By ID | `/greetings/{id}` | GET    |

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

**UC-01**

```text
POST http://localhost:8080/greetings
```

```json
{
  "userName": "Jyothish",
  "greetingMessage": "Hello, welcome!"
}
```

**UC-02**

```text
GET http://localhost:8080/greetings/1
```

## Architecture

### Controller

`GreetingServlet`

Handles HTTP requests and responses.

### Model

`Greeting`

Represents the greeting data.

### Service

`GreetingService`

Contains validation and business logic.

### DAO

`GreetingDAO`

Defines database operations.

`GreetingDAOImpl` implements the database operations using Plain JDBC.

### Database Connection

`DBConnection`

Creates the PostgreSQL connection.

## Use Case Status

```text
UC-01: Create Greeting       COMPLETED
UC-02: Get Greeting By ID    COMPLETED
```

## Author
