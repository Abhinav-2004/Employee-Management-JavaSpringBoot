
# 👨‍💼 Employee Management System (Spring Boot)

This is a full-stack **Employee Management System** backend built using **Spring Boot**. It provides RESTful APIs to manage employees, roles, users, and their relationships.

---

## 🔧 Features

- ✅ CRUD operations for Employees
- ✅ Role management
- ✅ User management with password encryption (BCrypt)
- ✅ Assign/remove roles to users
- ✅ Secure password update (with verification)
- ✅ RESTful API structure
- ✅ Comprehensive logging with SLF4J
- ✅ JUnit & Mockito-based unit testing

---

## 🧱 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **H2 / MySQL (JPA-compatible)**
- **Spring Web**
- **Spring Security (Password encoding)**
- **JUnit 5 + Mockito** for unit tests

---

## 📂 Project Structure

```
com.demo.employeeManagement
├── controller          # All REST controllers
├── dao                 # Repositories (Spring Data JPA)
├── entities            # JPA Entities: Employees, Users, Role
├── service             # Business logic
└── EmployeeManagementApplication.java
```

---

## 📦 API Endpoints

### 👩‍💼 Employee APIs
| Method | Endpoint                  | Description             |
|--------|---------------------------|-------------------------|
| GET    | `/employee/all`           | Get all employees       |
| GET    | `/employee/{id}`          | Get employee by ID      |
| POST   | `/employee/add`           | Add new employee        |
| POST   | `/employee/update/{id}`   | Update employee         |
| DELETE | `/employee/delete/{id}`   | Delete employee         |
| GET    | `/employee/department/{department}` | Get by department |
| GET    | `/employee/job/{job}`     | Get by job title        |

### 👤 User APIs
| Method | Endpoint                        | Description                      |
|--------|----------------------------------|----------------------------------|
| GET    | `/users/{email}`                | Get user by email                |
| POST   | `/users/add`                    | Add a new user                   |
| POST   | `/users/updatepassword/{id}`    | Update password                  |
| POST   | `/users/updateusername/{id}`    | Update username                  |
| DELETE | `/users/delete/{id}`            | Delete user                      |

### 🛡️ Role APIs
| Method | Endpoint                | Description           |
|--------|-------------------------|-----------------------|
| GET    | `/role/all`             | Get all roles         |
| POST   | `/role/add`             | Add new role          |
| POST   | `/role/update/{id}`     | Update role by ID     |
| DELETE | `/role/delete/{id}`     | Delete role by ID     |

### 🔁 User-Role APIs
| Method | Endpoint              | Description                      |
|--------|-----------------------|----------------------------------|
| POST   | `/user-role/add`      | Assign role to user              |
| DELETE | `/user-role/delete`   | Remove role from user            |

---

## 🔐 Password Security

- All passwords are stored using **BCrypt hashing** with strength 12.
- Password changes require verification of the old password.

---

## 🧪 Testing

Run unit tests using:

```bash
./mvnw test
```

Includes full unit test coverage for services and controllers using:

- **JUnit 5**
- **Mockito**

---

## 🚀 Running the App

### 1. Clone the repository

```bash
git clone https://github.com/your-username/employee-management-system.git
cd employee-management-system
```

### 2. Run with Maven

```bash
./mvnw spring-boot:run
```

Or run the main class:

```bash
EmployeeManagementApplication.java
```

### 3. Access API

App runs at: `http://localhost:8080`

---

## 🧑‍💻 Author

**Your Name**  
GitHub: [@yourusername](https://github.com/yourusername)

---

## 📝 License

This project is licensed under the MIT License.
