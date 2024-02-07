# Customer Information API

## Overview

This project is a RESTful API built with Spring Boot and PostgreSQL to manage customer information.

## Features

- **CRUD Operations:** Perform CRUD (Create, Read, Update, Delete) operations on customers data.
- **Database:** Use PostgreSQL for data storage.

## Technologies Used

- Spring Boot
- PostgreSQL
- Java
- Maven

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/salihaaaaa/customer-information-api.git

2. **Configure PostgreSQL database properties in application.properties:**

     ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.properties.hibernate.format_sql=true
     
   server.error.include-message=always

3. **Run the application**
4. **Access the application at http://localhost:8080/api/customer**

 ## API Testing with Postman
   Execute the requests in Postman to test the API endpoints.

   **Create Customer:** POST localhost:8080/api/customer

   **Get Customer:** GET localhost:8080/api/customer & GET localhost:8080/api/customer/{id}

   **Update Customer:** UPDATE localhost:8080/api/customer/{id}

   **Delete Customer:** DELETE localhost:8080/api/customer/{id}

  
