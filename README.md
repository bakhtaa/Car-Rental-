#  Car Rental Management System

##  Project Overview

Car Rental Management System is a Spring Boot web application developed for managing a car rental agency. The application allows administrators to manage vehicles, brands, options and reservations through an interactive dashboard.

The project follows a layered architecture using:

- Controller Layer
- Service Layer
- Repository Layer
- DTO Pattern
- Entity Relationships using JPA

The application also integrates image upload, dashboard statistics, pagination and search functionality.

---

#  Technologies Used

### Backend
- Java 17+
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Lombok

### Frontend
- Thymeleaf
- HTML
- CSS
- Bootstrap 5
- Font Awesome

### Database
- MySQL

### Build Tool
- Maven

---

#  Project Architecture

```
src
 ├── controller
 │      CarController
 │
 ├── service
 │      IgestionCar
 │      CarServiceImpl
 │
 ├── repository
 │      CarRepository
 │      BrandRepository
 │      OptionRepository
 │
 ├── dto
 │      CarDashboardDTO
 │
 ├── entity
 │      Car
 │      Brand
 │      Option
 │      CarDetails
 │      Reservation
 │
 └── templates
        home.html
        addCar.html
```

---

#  Features

## Car Management (CRUD)

The application supports complete CRUD operations:

### Add Car
- Add a new car
- Upload image
- Select brand
- Select multiple options

### Edit Car
- Modify existing vehicle information
- Keep selected values automatically

### Delete Car
- Remove vehicle from database

### Display Cars
- Card based display
- Image rendering
- Car information visualization

---

# Search Functionality

Users can search vehicles by model.

Implemented using Spring Data JPA:

```java
findByModelContains(String mc)
```

Spring automatically generates:

```sql
SELECT *
FROM Car
WHERE model LIKE '%keyword%';
```

---

#  Pagination

Pagination was implemented using:

```java
Page<Car>
PageRequest
Pageable
```

Benefits:

- Improves performance
- Avoids loading all records
- Better user experience

---

#  Dashboard KPI

The project contains a dashboard displaying:

- Total number of cars
- Available cars
- Total fleet value
- Cars grouped by brand

Statistics are computed using Java Streams.

Example:

```java
cars.stream()
.filter(Car::isAvailable)
.count();
```

---

#  DTO Implementation

DTO pattern was implemented through:

```java
CarDashboardDTO
```

Purpose:

- Transfer dashboard statistics
- Reduce coupling
- Improve architecture quality

---

#  Java Streams Usage

Streams were used for:

### Counting available cars

```java
cars.stream()
.filter(Car::isAvailable)
.count();
```

### Calculating total value

```java
cars.stream()
.mapToDouble(Car::getPricePerDay)
.sum();
```

### Grouping by brand

```java
Collectors.groupingBy()
```

---

#  JPA Relationships

The project uses several relationships:

### Many-To-One

Car → Brand

### Many-To-Many

Car ↔ Option

### One-To-One

Car ↔ CarDetails

### One-To-Many

Car → Reservation

---

# Image Upload

Users can upload car images.

Images are:

- stored in uploads folder
- saved in database as filename
- displayed dynamically in UI

---

# Installation

Clone repository:

```bash
git clone your_repository_link
```

Open project:

```bash
Open using IntelliJ IDEA
```

Configure database:

application.properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental
spring.datasource.username=root
spring.datasource.password=your_password
```

Run:

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080/home
```

---

#  Author

By Bakhta Nairouz Ben Hadj Slama

Spring Boot • JPA • Thymeleaf • DTO • Streams

