# Safari Smart

A web-based safari tourism booking management system built with Spring Boot 4 and Thymeleaf.

## Features

### User Features
- Browse safari packages by category (National Parks, Beach & Island, Cultural Tours, Adventure, Luxury)
- View detailed package information with images, pricing, and itineraries
- Register and login securely
- Book safari packages with guest checkout support
- Make payments via Mobile Money, Bank Transfer, or Cash
- View payment receipts (printable)
- User dashboard with package browsing

### Admin Features
- Admin dashboard with statistics (bookings, payments, users)
- Full CRUD management for safari packages
- Full CRUD management for users
- View and manage all bookings
- Payment verification workflow (verify/reject payments)
- Payment dashboard with pending/verified counts

## Tech Stack

- **Backend**: Java 17, Spring Boot 4.1.0, Spring MVC, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, HTML5, CSS3, Font Awesome
- **Database**: PostgreSQL
- **Build**: Maven
- **Other**: Lombok, Jakarta Validation, Spring DevTools

## Project Structure

```
src/main/java/com/safarismart/safarismart/
├── config/
│   ├── DataInitializer.java    # Seeds sample data on startup
│   └── SecurityConfig.java     # Security configuration
├── controller/
│   ├── BookingController.java  # Booking CRUD endpoints
│   ├── HomeController.java     # Home, login, signup, dashboards
│   ├── PaymentController.java  # Payment management
│   ├── SafariPackageController.java  # Package CRUD
│   └── UserController.java     # User CRUD
├── model/
│   ├── Booking.java
│   ├── BookingStatus.java      # PENDING, CONFIRMED, CANCELLED, COMPLETED
│   ├── Category.java
│   ├── Payment.java
│   ├── PaymentStatus.java      # PENDING, VERIFIED, REJECTED
│   ├── SafariPackage.java
│   ├── User.java
│   └── UserRole.java           # ADMIN, USER
├── repository/                 # Spring Data JPA repositories
├── service/                    # Business logic layer
└── SafariSmartApplication.java # Main entry point
```

## Prerequisites

- Java 17+
- PostgreSQL
- Maven (included via Maven Wrapper)

## Setup & Running

1. **Create the database**:
   ```sql
   CREATE DATABASE safarismart_db;
   ```

2. **Configure database** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/safarismart_db
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```

3. **Run the application**:
   ```bash
   .\mvnw spring-boot:run
   ```

4. **Open** `http://localhost:8080` in your browser.

## Sample Data

The app automatically seeds sample data on first run:

### Categories
- National Parks, Beach & Island, Cultural Tours, Adventure, Luxury

### Packages (16 total)
- Serengeti Great Migration ($3,500), Ngorongoro Crater ($2,800), Kilimanjaro Trekking ($4,500), Zanzibar Beach Holiday ($1,800), Luxury Safari Lodge ($8,000), and more

### Test Accounts

| Role  | Email                  | Password |
|-------|------------------------|----------|
| Admin | admin@safarismart.com  | admin123 |
| User  | john@example.com       | user123  |
| User  | jane@example.com       | user123  |

### Sample Bookings & Payments
- 5 sample bookings with various statuses
- 2 sample payments (one verified, one pending)

## API Endpoints

### Public
| Method | URL              | Description      |
|--------|------------------|------------------|
| GET    | `/`              | Home page        |
| GET    | `/login`         | Login page       |
| POST   | `/login`         | Login action     |
| GET    | `/signup`        | Signup page      |
| POST   | `/signup`        | Signup action    |
| GET    | `/logout`        | Logout           |

### Admin (no authentication barrier currently)
| Method | URL                              | Description            |
|--------|----------------------------------|------------------------|
| GET    | `/admin/dashboard`               | Admin dashboard        |
| GET    | `/user/dashboard`                | User dashboard         |
| GET    | `/packages`                      | List packages          |
| GET    | `/packages/{id}`                 | View package details   |
| GET    | `/packages/new`                  | Create package form    |
| GET    | `/packages/{id}/edit`            | Edit package form      |
| POST   | `/packages/save`                 | Save package           |
| GET    | `/packages/{id}/delete`          | Delete package         |
| GET    | `/bookings`                      | List bookings          |
| GET    | `/bookings/new`                  | Create booking form    |
| POST   | `/bookings/save`                 | Save booking           |
| GET    | `/bookings/{id}/status/{status}` | Update booking status  |
| GET    | `/bookings/{id}/delete`          | Delete booking         |
| GET    | `/payments`                      | List payments          |
| GET    | `/payments/dashboard`            | Payment dashboard      |
| GET    | `/payments/new/{bookingId}`      | Payment form           |
| POST   | `/payments/save`                 | Submit payment         |
| GET    | `/payments/verify/{id}`          | Verify payment         |
| GET    | `/payments/reject/{id}`          | Reject payment         |
| GET    | `/payments/receipt/{bookingId}`  | View receipt           |
| GET    | `/users`                         | List users             |
| GET    | `/users/new`                     | Create user form       |
| GET    | `/users/{id}/edit`               | Edit user form         |
| POST   | `/users/save`                    | Save user              |
| GET    | `/users/{id}/delete`             | Delete user            |
