# Seat On Wheels

A backend-focused intercity bus ticket booking system built with Java and Spring Boot.

Seat On Wheels is designed as a modular monolith that models buses, routes, trips, seats, trip-specific seat inventory, users, and bookings. The project focuses on practical backend engineering problems such as relational data modeling, transactional booking, concurrency control, authentication, caching, and API security.

---

## Tech Stack

| Category | Technologies |
|---|---|
| Language | Java 21 |
| Backend | Spring Boot, Spring MVC |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Build Tool | Maven |
| API Testing | Postman |
| Version Control | Git, GitHub |

---

## Architecture

Seat On Wheels follows a **modular monolith architecture** with separation between controllers, services, repositories, entities, security, and exception handling.

```text
                        Client
                          |
                          v
                    REST Controllers
                          |
                          v
                     Service Layer
                   /      |       \
                  /       |        \
                 v        v         v
              Booking    Trip      Auth
              Module    Module    Module
                 |        |         |
                 v        v         v
             Repository Layer   Security
                    |
                    v
                PostgreSQL