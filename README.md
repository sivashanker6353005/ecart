# 🛒 Product Service - Spring Boot Microservice

This is a backend microservice for managing products in an e-commerce system. Built using **Spring Boot**, **MySQL**, and **Testcontainers** for integration testing.

---

## 🚀 Features

- Add new products (POST)
- Retrieve all products (GET)
- MySQL DB support
- Testcontainers-based integration tests
- RESTful API with JSON support

---

## 🧰 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL (Testcontainers for testing)
- JUnit 5 + MockMvc
- Docker (for Testcontainers)
- Maven

---

## 🏁 Getting Started

### Prerequisites

- JDK 17+
- Maven 3.8+
- Docker (required for running Testcontainers)
- MySQL (for development DB)

---

### 🛠️ Build & Run

```bash
# Clone the repo
git clone https://github.com/sivashanker6353005/product-service.git
cd product-service

# Run the application
./mvnw spring-boot:run
