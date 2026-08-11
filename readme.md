# Greetings App

A Java web application for creating greetings using Java Servlets, Plain JDBC, PostgreSQL, Maven, Jackson, Apache Tomcat, and Postman.

## UC-01: Create Greeting

The user can create a greeting by providing:

* User name
* Greeting message

The greeting is stored in the PostgreSQL database.

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

Use `db.properties.example`:

```properties
db.url=jdbc:postgresql://localhost:5432/greetings_app
db.username=postgres
db.password=YOUR_PASSWORD
```

## API

### Create Greeting

**Method**

```text
POST
```

**URL**

```text
http://localhost:8080/greetings
```

### Request Body

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

## Architecture

### Controller

`GreetingServlet`

Receives the HTTP request and sends the response.

### Model

`Greeting`

Contains:

```text
greetingId
userName
greetingMessage
createdDate
```

### Service

`GreetingService`

Contains the business logic and validation.

### DAO

`GreetingDAO`

Defines database operations.

`GreetingDAOImpl` contains the Plain JDBC implementation.

### Database Connection

`DBConnection`

Creates the PostgreSQL database connection using JDBC.

## How to Run

### 1. Clone the Project

```bash
git clone <your-github-repository-url>
cd Greetings-App
```

### 2. Create the Database

```sql
CREATE DATABASE greetings_app;
```

### 3. Create the Table

Run the `greetings` table SQL given above.

### 4. Configure Database

Create `db.properties` and add your PostgreSQL credentials.

### 5. Build the Project

```bash
mvn clean package
```

### 6. Run with Tomcat

Deploy the WAR file to Apache Tomcat and start the server.

### 7. Test with Postman

```text
POST http://localhost:8080/greetings
```

Send the JSON request shown above.

## UC-01 Status

```text
UC-01: Create Greeting
Status: COMPLETED
```
