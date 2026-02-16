# Backend-Technical-Assessment
Student registration management system

# Student Enrollment REST API

A Spring Boot RESTful API for managing students, courses, and student course enrollments. This project implements a many-to-many relationship between students and courses, utilizes an in-memory database for easy setup, and secures modifying endpoints using Spring Security.

## 🚀 Tech Stack

* **Java 17+**
* **Spring Boot 3.x** (Web, Data JPA, Security)
* **H2 Database** (In-memory)
* **Maven**
* **JUnit 5 / Mockito** (Testing)

## ✨ Features

* **CRUD Operations:** Manage Students and Courses easily.
* **Many-to-Many Relationships:** Enroll students in multiple courses without duplicating records.
* **Spring Security Integration:** * `GET` requests (fetching data) are completely public.
    * `POST`, `PUT`, and `DELETE` requests are secured using Basic Authentication.
* **Pagination:** Fetch large sets of student data efficiently using pagination parameters.
* **Built-in Database UI:** Access the H2 console directly from the browser to inspect tables and run queries.

---

## 🛠️ Setup and Installation

### Prerequisites
* Java Development Kit (JDK) 17 or higher installed.
* Maven installed (or use the provided Maven wrapper `mvnw`).

### Running the Application Locally
1. Clone the repository:
   ```bash
   git clone [https://github.com/yckarachchi/Backend-Technical-Assessment.git](https://github.com/yckarachchi/Backend-Technical-Assessment.git)
   cd Backend-Technical-Assessment
Run the application using the Maven wrapper:

Bash
./mvnw spring-boot:run
(On Windows, run: mvnw spring-boot:run)

The server will start on port 8080.

🗄️ Database Access (H2 Console)
This project uses an in-memory H2 database. Data is reset every time the application restarts.

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:studentdb

Username: sa

Password: password

🔒 Security Credentials (Basic Auth)
To test the secured endpoints (POST, PUT, DELETE), use the following Basic Authentication credentials in Postman or cURL:

Username: admin

Password: password

📡 API Endpoints & Sample Requests
1. Create a Student (Secured)
Method: POST

URL: /students

Body (JSON):

JSON
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "age": 22
}
2. Create a Course (Secured)
Method: POST

URL: /courses

Body (JSON):

JSON
{
  "name": "Introduction to Java",
  "description": "Learn Spring Boot basics",
  "credits": 3
}
3. Get All Students (Public - Supports Pagination)
Method: GET

URL: /students

(Optional Pagination): /students?page=0&size=5

4. Enroll a Student in a Course (Secured)
Method: POST

URL: /students/{studentId}/enroll/{courseId}

Example: /students/123e4567-e89b-12d3-a456-426614174000/enroll/1

Description: Adds the specified course to the student's enrollment list.

🧪 Running Tests
To run the automated JUnit test suite for the controllers and services, execute the following command in your terminal:

Bash
./mvnw test
