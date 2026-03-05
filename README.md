# XYZ Retail API

A comprehensive retail management API built with **Spring Boot** and designed using **Clean Architecture** principles to ensure scalability, maintainability, and clear separation of concerns.

## 🚀 Overview

The **XYZ Retail API** provides a full-featured backend platform for managing:

- Products
- Shopping carts
- Orders
- Sales & reporting

Built with clean architecture, it ensures each layer is modular, testable, and independent of frameworks.

## ✨ Features

### 🛒 Product Management
- Create and update products
- Search products by name
- Track inventory levels

### 🛍 Shopping Cart
- Add products to cart
- View cart contents
- Persistent cart storage

### 📦 Order Processing
- Place customer orders
- Track order history
- Find orders by ID

### 📊 Reporting
- Top-selling products of the day
- Least-selling products of the month
- Sales reports by date range

## 🧱 Architecture

This project follows **Clean Architecture**, separating business logic from external concerns:

- **Domain Layer** — Core business entities & logic
- **Application Layer** — Use cases & ports
- **Infrastructure Layer** — Database, adapters, external frameworks
- **Presentation Layer** — REST controllers & DTOs

## 🧰 Tech Stack

- **Java 25**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **H2 Database** (development)
- **Flyway** for DB migrations
- **Gradle** (build tool)
- **Spotless** (code formatting)
- **Swagger / OpenAPI** for API documentation

## 📚 API Endpoints

### Products
```
GET  /api/v1/products/search?name={name}
POST /api/v1/products
```

### Cart
```
POST /api/v1/cart/items
GET  /api/v1/cart
```

### Orders
```
POST /api/v1/orders
GET  /api/v1/orders/{id}
```

### Reports
```
GET  /api/v1/reports/products/top-selling
GET  /api/v1/reports/products/least-selling
POST /api/v1/reports/sales/by-date-range
```

## 🏁 Getting Started

### Prerequisites
- Java **17+**
- Gradle **7.x+**

### Installation

Clone the repository:
```bash
git clone https://github.com/voltdev/backend-engineer-assignment.git
cd retail-api
```

Build the project:
```bash
./gradlew build
```

Run the application:
```bash
./gradlew bootRun
```

API:
```
http://localhost:8080
```
Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

## 🗄 Database

Access H2 console:
```
http://localhost:8080/h2-console
```

Credentials:
```
JDBC URL: jdbc:h2:mem:retaildb
Username: sa
Password: password
```

## ✅ Code Quality

Format code:
```bash
./gradlew spotlessApply
```

Check formatting:
```bash
./gradlew spotlessCheck
```

## 🔐 Security

Role-based access control:

- Public: Product search, cart operations, placing orders
- Employee: Order search
- Management: Reporting APIs

See `SecurityConfig.java` for auth setup.

## 🧪 Tests
```bash
./gradlew test
```

## 📁 Project Structure
```
com.xyz.retail
  ├── product
  │   ├── application
  │   ├── domain
  │   ├── infrastructure
  │   └── presentation
  ├── cart
  │   ├── application
  │   ├── domain
  │   ├── infrastructure
  │   └── presentation
  ├── order
  │   ├── application
  │   ├── domain
  │   ├── infrastructure
  │   └── presentation
  └── reporting
      ├── application
      ├── domain
      ├── infrastructure
      └── presentation
```

## 🧠 Design Decisions

- Clean Architecture
- Domain‑Driven Design
- CQRS pattern
- REST‑based API design

## 📬 Postman Collection
Located under:
```
postman/
```
