# Kaiburr Task 1 - REST API with Spring Boot and MongoDB

This project is a RESTful API built using Spring Boot, designed for managing tasks and executing command-line operations with tracking and history.

## 📌 Features Implemented

- Create a new Task (`POST /tasks`)
- View all Tasks (`GET /tasks`)
- Search Tasks by name (`GET /tasks/search?name=...`)
- Delete a Task by ID (`DELETE /tasks/{id}`)
- Execute a command (`PUT /tasks/{id}/execute`)
- MongoDB is used for storage with `Task` and embedded `TaskExecution` tracking

## ⚙️ Technologies Used

- Java 17
- Spring Boot 3
- Maven
- MongoDB
- Postman (for testing)

## 📂 Folder Structure

```
kaiburr-task-1-rest-api/
├── src/
│   ├── main/
│   │   ├── java/com/kaiburr/task/
│   │   │   ├── controller/TaskController.java
│   │   │   ├── model/Task.java, TaskExecution.java
│   │   │   ├── repository/TaskRepository.java
│   │   │   ├── service/TaskService.java
│   │   │   └── TaskApplication.java
│   │   └── resources/application.properties
└── pom.xml
```

## 🚀 Running the Project

1. Make sure MongoDB is running:
   ```bash
   mongod
   ```

2. Start the Spring Boot server:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Open Postman and test endpoints like:
   - `POST http://localhost:8080/tasks`
   - `GET http://localhost:8080/tasks`
   - `DELETE http://localhost:8080/tasks/{id}`
   - `PUT http://localhost:8080/tasks/{id}/execute`

## 🧪 Sample Task JSON (for POST)

```json
{
  "id": "123",
  "name": "Test Task",
  "owner": "Thrisha",
  "command": "echo Hello Kaiburr"
}
```

## 📸 Screenshots

Add the following screenshots in a `/screenshots` folder:
- Server running (with timestamp)
- Postman `POST /tasks`
- Postman `GET /tasks`
- Postman `GET /tasks/search`
- Postman `DELETE /tasks/{id}`
- Postman `PUT /tasks/{id}/execute`

