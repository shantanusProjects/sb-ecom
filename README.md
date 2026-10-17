# SB ECOM

A Spring Boot-based e-commerce backend for managing products, categories, cart, orders, addresses, and authentication. This project uses PostgreSQL by default, Spring Security with JWT, and SpringDoc OpenAPI for API documentation.

## Project Overview

This application exposes a REST API for an online store and includes:

- User registration and login
- JWT-based authentication and authorization
- Role-based users: USER, SELLER, ADMIN
- Category management
- Product catalog with pagination and search
- Cart operations
- Address management
- Order placement and payment metadata
- Swagger UI for API testing

## Tech Stack

- Java 21
- Spring Boot 4.0.3
- Spring Web MVC
- Spring Data JPA
- Spring Security
- PostgreSQL
- JWT (jjwt)
- ModelMapper
- Lombok
- SpringDoc OpenAPI / Swagger UI

## Project Structure

```text
sb-ecom/
├── src/
│   ├── main/
│   │   ├── java/com/ecommerce/project/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── exceptions/
│   │   │   ├── model/
│   │   │   ├── payload/
│   │   │   ├── repositories/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   ├── util/
│   │   │   └── SbEcomApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── images/
│   └── test/java/com/ecommerce/project/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Default Database Configuration

The project is configured to use PostgreSQL in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

The file also contains commented-out configuration for H2 and MySQL if you want to switch databases.

## Required Tools

- Java 21+
- Maven or Maven wrapper
- PostgreSQL running locally or a reachable database instance

## Run the Application

From the project root:

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux/macOS

```bash
./mvnw spring-boot:run
```

The application starts on the default Spring Boot port:

```text
http://localhost:8080
```

## Swagger UI

Open the API documentation here:

```text
http://localhost:8080/swagger-ui/index.html
```

or the legacy path:

```text
http://localhost:8080/swagger-ui.html
```

## Authentication and Roles

The application uses JWT-based authentication with Spring Security.

### Seeded demo users

The app creates demo users at startup via `WebSecurityConfig`:

- Username: `user1` | Password: `password1` | Role: `ROLE_USER`
- Username: `seller1` | Password: `password2` | Role: `ROLE_SELLER`
- Username: `admin` | Password: `adminPass` | Role: `ROLE_ADMIN`

### Auth endpoints

```text
POST /api/auth/signup
POST /api/auth/signin
POST /api/auth/signout
GET  /api/auth/username
GET  /api/auth/user
```

## API Modules

### 1. Authentication

- `POST /api/auth/signup` — register a new user
- `POST /api/auth/signin` — login and receive JWT token in cookie
- `POST /api/auth/signout` — clear the JWT cookie
- `GET /api/auth/user` — fetch the current authenticated user

### 2. Categories

- `GET /api/public/categories` — fetch categories with pagination and sorting
- `POST /api/public/categories` — create category
- `PUT /api/public/categories/{categoryId}` — update category
- `DELETE /api/admin/categories/{categoryId}` — delete category

### 3. Products

- `GET /api/public/products` — get all products with pagination
- `GET /api/public/categories/{categoryId}/products` — get products by category
- `GET /api/public/products/keyword/{keyword}` — search products by keyword
- `POST /api/admin/categories/{categoryId}/product` — add product under a category
- `PUT /api/admin/products/{productId}` — update product details
- `DELETE /api/admin/products/{productId}` — delete product
- `PUT /api/products/{productId}/image` — upload/update product image

### 4. Cart

- `POST /api/carts/products/{productId}/quantity/{quantity}` — add product to cart
- `GET /api/carts` — get all carts
- `GET /api/carts/users/cart` — get logged-in user's cart
- `PUT /api/cart/products/{productId}/quantity/{operation}` — increment or decrement product quantity
- `DELETE /api/carts/{cartId}/product/{productId}` — remove product from cart

### 5. Address

- `POST /api/addresses` — add address for logged-in user
- `GET /api/addresses` — get all addresses
- `GET /api/addresses/{addressId}` — get address by ID
- `GET /api/users/addresses` — get all addresses for logged-in user
- `PUT /api/addresses/{addressId}` — update address
- `DELETE /api/addresses/{addressId}` — delete address

### 6. Orders

- `POST /api/order/users/payments/{paymentMethod}` — place order using a chosen payment method

## Security Notes

The security configuration allows open access to:

- `/api/auth/**`
- `/v3/api-docs/**`
- `/swagger-ui/**`
- `/swagger-ui.html`
- `/images/**`
- `/api/test/**`
- `/h2-console/**`

All other endpoints require authentication.

## Default Application Properties

Key values in `application.properties`:

```properties
project.image=src/main/resources/images
spring.app.jwtSecret=...
spring.app.jwtExpirationMS=300000
spring.ecom.app.jwtCookieName=springBootEcom
```

## Notes

- Product images are stored under `src/main/resources/images`.
- The app uses `ddl-auto=update`, so database tables are generated automatically.
- The codebase includes custom exception handling and API response wrappers to standardize errors.

## License

No explicit license file is included in the repository, so usage rights are not defined here.
