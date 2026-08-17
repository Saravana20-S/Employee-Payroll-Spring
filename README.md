# Employee Payroll, Address Book & Greetings System

## Objective

The **Employee Payroll, Address Book & Greetings System** is a Spring Boot REST API application developed to manage employee information, employee addresses, payroll details, and greeting messages.

The project demonstrates the implementation of:

* Spring Boot
* REST APIs
* Spring Data JPA / Hibernate
* DTOs
* Bean Validation
* PostgreSQL/MySQL database integration
* WebClient for external REST API communication
* Global exception handling
* Transaction management
* Spring AOP
* Logging

The system follows a layered architecture:

```text
Controller
    ↓
DTO / Validation
    ↓
Service
    ↓
Entity
    ↓
Repository
    ↓
Database
```

The BRD specifies Employee, Address, Department, and Payroll as the primary entities with their required JPA relationships.

---

## Features

### 1. Employee Management

The Employee module provides complete CRUD functionality.

* Create employee
* Retrieve all employees
* Retrieve employee by ID
* Update employee
* Delete employee
* Associate an employee with a department
* Maintain employee salary information

### 2. Address Management

The Address module allows address information to be associated with an employee.

* Add employee address
* Retrieve employee address
* Update employee address

The employee and address relationship is implemented using `@OneToOne`.

### 3. Payroll Management

The Payroll module handles employee salary calculations.

* Create payroll
* Retrieve employee payroll
* Obtain employee salary from the Employee entity
* Accept deductions through a request DTO
* Calculate net salary
* Associate payroll with an employee

Payroll uses an `@ManyToOne` relationship from Payroll to Employee, while Employee maintains the `@OneToMany` collection of payroll records.

The payroll calculation follows:

```text
Net Salary = Employee Salary - Deductions
```

Payroll creation is also designed to use `@Transactional` as required by the BRD.

### 4. Greetings

The Greetings module provides a greeting message for a supplied name.

```text
GET /api/greetings/{name}
```

The response is represented using `GreetingResponseDTO`.

### 5. External Employee Details

The system is designed to retrieve employee information from an external REST service using Spring WebClient.

The external response is mapped into `ExternalEmployeeDTO`.

External-service failures are handled separately and should return `502 BAD GATEWAY`.

### 6. Validation

Incoming request data is validated using Jakarta Bean Validation annotations such as:

* `@NotBlank`
* `@NotNull`
* `@Email`
* `@Pattern`
* `@Size`
* `@Positive`
* `@PositiveOrZero`

Invalid request data should return `400 BAD REQUEST`.

### 7. Global Exception Handling

The application uses `@RestControllerAdvice` for centralized exception handling.

Custom exceptions include:

* `EmployeeNotFound`
* `AddressNotFound`
* `DepartmentNotFound`
* `PayrollNotFound`

The global exception handler provides standardized error responses instead of handling exceptions separately inside every controller.

The BRD specifies an error response containing:

```text
timestamp
status
error
message
path
```

### 8. Transaction Management

`@Transactional` is used for operations requiring multiple database actions, especially payroll creation.

If an operation fails, the transaction should roll back to maintain database consistency.

### 9. AOP and Logging

Spring AOP is used for cross-cutting logging concerns.

The AOP implementation is intended to capture:

* Service method name
* Execution time
* Successful execution
* Failed execution

SLF4J/Logback is used for application logging at levels such as:

* INFO
* DEBUG
* WARN
* ERROR

---

## API Endpoints

The BRD defines 12 REST endpoints.

| #  | Method | Endpoint                               | Purpose                       |
| -- | ------ | -------------------------------------- | ----------------------------- |
| 1  | POST   | `/api/employees`                       | Create employee               |
| 2  | GET    | `/api/employees`                       | Get all employees             |
| 3  | GET    | `/api/employees/{id}`                  | Get employee by ID            |
| 4  | PUT    | `/api/employees/{id}`                  | Update employee               |
| 5  | DELETE | `/api/employees/{id}`                  | Delete employee               |
| 6  | POST   | `/api/employees/{id}/address`          | Add employee address          |
| 7  | GET    | `/api/employees/{id}/address`          | Get employee address          |
| 8  | PUT    | `/api/employees/{id}/address`          | Update employee address       |
| 9  | POST   | `/api/employees/{id}/payroll`          | Create payroll                |
| 10 | GET    | `/api/employees/{id}/payroll`          | Get employee payroll          |
| 11 | GET    | `/api/employees/{id}/external-details` | Get external employee details |
| 12 | GET    | `/api/greetings/{name}`                | Get greeting                  |

---

## JPA Entity Relationships

The project contains four primary entities:

```text
Employee
   │
   ├────────────── 1 : 1 ────────────── Address
   │
   ├────────────── N : 1 ────────────── Department
   │
   └────────────── 1 : N ────────────── Payroll
```

### Employee → Address

```java
@OneToOne
@JoinColumn(name = "address_id", unique = true)
private Address address;
```

### Employee → Department

```java
@ManyToOne
@JoinColumn(name = "dept_id")
private Department department;
```

### Employee → Payroll

```java
@OneToMany(mappedBy = "employee")
private List<Payroll> payrolls = new ArrayList<>();
```

### Payroll → Employee

```java
@ManyToOne
@JoinColumn(name = "employee_id")
private Employee employee;
```

---

## DTOs

JPA entities are not directly exposed through REST APIs.

### Request DTOs

```text
EmployeeRequestDTO
PayrollRequestDTO
AddressRequestDTO
```

### Response DTOs

```text
EmployeeResponseDTO
PayrollResponseDTO
AddressResponseDTO
GreetingResponseDTO
ExternalEmployeeDTO
```

This follows the BRD requirement that entities must not be exposed directly through REST APIs.

---

## Project Structure

```text
src
└── main
    └── java
        └── com.bridgelabz.employeepayrolladdressgreeting
            │
            ├── controller
            │   ├── EmployeeController
            │   ├── AddressController
            │   ├── PayrollController
            │   └── GreetingController
            │
            ├── dto
            │   ├── request
            │   │   ├── EmployeeRequestDTO
            │   │   ├── AddressRequestDTO
            │   │   └── PayrollRequestDTO
            │   │
            │   └── response
            │       ├── EmployeeResponseDTO
            │       ├── AddressResponseDTO
            │       ├── PayrollResponseDTO
            │       ├── GreetingResponseDTO
            │       └── ExternalEmployeeDTO
            │
            ├── model
            │   ├── Employee
            │   ├── Address
            │   ├── Department
            │   └── Payroll
            │
            ├── repository
            │   ├── EmployeeRepository
            │   ├── AddressRepository
            │   ├── DepartmentRepository
            │   └── PayrollRepository
            │
            ├── service
            │   ├── EmployeeService
            │   ├── EmployeeServiceImpl
            │   ├── AddressService
            │   ├── AddressServiceImpl
            │   ├── PayrollService
            │   └── PayrollServiceImpl
            │
            └── exception
                ├── EmployeeNotFound
                ├── AddressNotFound
                ├── DepartmentNotFound
                ├── PayrollNotFound
                ├── ErrorResponse
                └── GlobalExceptionHandler
```

---

## HTTP Status Codes

The application follows the status codes defined in the BRD.

| Status                    | Usage                      |
| ------------------------- | -------------------------- |
| 200 OK                    | Successful GET / PUT       |
| 201 CREATED               | Successful POST            |
| 204 NO CONTENT            | Successful DELETE          |
| 400 BAD REQUEST           | Validation failure         |
| 404 NOT FOUND             | Resource does not exist    |
| 409 CONFLICT              | Duplicate/conflicting data |
| 500 INTERNAL SERVER ERROR | Unexpected server error    |
| 502 BAD GATEWAY           | External API failure       |

---

## Testing

The REST APIs are tested using Postman.

Testing includes:

* Employee CRUD operations
* Address creation, retrieval, and update
* Payroll creation and retrieval
* Salary and deduction calculation
* Greeting endpoint
* Validation failures
* Resource-not-found scenarios
* Global exception handling
* HTTP status code verification
* External API integration

Example payroll request:

```json
{
    "deductions": 5000
}
```

Example payroll response:

```json
{
    "id": 1,
    "basicSalary": 50000,
    "deductions": 5000,
    "netSalary": 45000,
    "employeeId": 1
}
```

---

## Technology Stack

The BRD specifies the following technologies:

* **Java**
* **Spring Boot**
* **Spring Web / REST**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL/MySQL**
* **Jakarta Bean Validation**
* **WebClient**
* **Spring AOP**
* **SLF4J / Logback**
* **Maven**
* **Lombok**

---

## Architecture

The application follows a layered architecture:

```text
                 Client
                   │
                   ▼
             REST Controller
                   │
                   ▼
            Request DTO
             + Validation
                   │
                   ▼
                Service
                   │
                   ▼
                Entity
                   │
                   ▼
              Repository
                   │
                   ▼
                Database
```

Cross-cutting components:

```text
AOP ──────────────────→ Logging
Global Exception Handler → Standardized Errors
WebClient ─────────────→ External REST API
@Transactional ────────→ Transaction Boundaries
```

This architecture follows the proposed architecture in the BRD.

---

## Current Development Progress

### Completed

* Employee module
* Employee CRUD APIs
* Address module
* Address APIs
* Payroll entity
* Payroll DTOs
* Payroll repository
* Payroll service
* Payroll controller
* Payroll salary calculation
* Payroll API testing
* Custom exceptions
* Initial global exception handler

### Remaining

* Greeting endpoint
* External Employee Details using WebClient
* Complete validation exception handling
* Complete global exception handling
* Transaction verification
* AOP logging
* Execution-time logging
* Final API testing and integration verification

## Outcome

The project provides a layered Spring Boot REST API for managing employees, addresses, payroll information, and greetings.

It demonstrates practical implementation of RESTful APIs, JPA entity relationships, DTO-based communication, validation, exception handling, transaction management, WebClient integration, AOP, and logging.

The architecture is designed to remain maintainable, reliable, scalable, and extensible for future modules and features, as specified by the BRD.
