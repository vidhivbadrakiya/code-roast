# Code Roast

A functional Java Spring Boot API that performs automated code reviews with humor and insight. Developers submit code snippets, and the API responds with "roasts" (funny yet constructive feedback) along with improvement tips. The goal: make code reviews a little more human and a lot more fun.

## Features

- **Automated Code Reviews**: Submit code snippets and receive humorous yet constructive feedback.
- **Humorous Roasts**: Randomly generated funny comments about your code.
- **Constructive Tips**: Practical improvement suggestions.
- **Persistence**: All submissions are stored in PostgreSQL database.
- **RESTful API**: Easy-to-use endpoints for integration.

## API Endpoints

### Submit Code for Review
- **POST** `/api/roast/submit`
- **Body**:
  ```json
  {
    "code": "public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello, World!\"); } }",
    "language": "java"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "code": "public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello, World!\"); } }",
    "language": "java",
    "submittedAt": "2023-11-08T10:30:00",
    "roast": "This code is so bad, even the compiler is laughing at it!",
    "tips": "Consider using more descriptive variable names to improve readability. Add comments to explain complex logic."
  }
  ```

### Get All Submissions
- **GET** `/api/roast/snippets`
- **Response**: Array of all code submissions with their reviews.

### Get Specific Submission
- **GET** `/api/roast/snippet/{id}`
- **Response**: Details of a specific code submission.

## Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd code-roast
   ```

2. Set up PostgreSQL database:
   ```sql
   CREATE DATABASE coderoast;
   ```

3. Update database configuration in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/coderoast
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## Testing

You can test the API using tools like Postman or curl:

```bash
curl -X POST http://localhost:8080/api/roast/submit \
  -H "Content-Type: application/json" \
  -d '{"code":"public class Test { }","language":"java"}'
```

## Architecture

The application follows a layered architecture typical of Spring Boot applications:

- **Controller Layer**: Handles HTTP requests and responses using RESTful endpoints.
- **Service Layer**: Contains business logic, including roast and tip generation algorithms.
- **Repository Layer**: Manages data persistence using Spring Data JPA.
- **Entity Layer**: Defines the data model with JPA annotations.

## Testing

Comprehensive unit tests are implemented for controllers and services using JUnit and Mockito. Tests cover API endpoints, service methods, and edge cases to ensure reliability.

Run tests with:
```bash
mvn test
```

## Future Enhancements

- Add support for multiple programming languages with language-specific roasts.
- Implement user authentication and personalized roast history.
- Integrate AI/ML for more intelligent code analysis and feedback.
- Add a web frontend for easier interaction.

## Technologies Used

- **Spring Boot 3.2.0**: Framework for building the REST API
- **Spring Data JPA**: For database operations
- **PostgreSQL**: Database for persistence
- **Maven**: Build tool and dependency management
- **JUnit & Mockito**: For unit testing

## Contributing

Feel free to submit issues and enhancement requests!

## License

This project is licensed under the MIT License - see the LICENSE file for details.