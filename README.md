# MediFlow Microservices Project

**MediFlow** is a **Spring Boot microservices-based application** for managing medical services, appointments, billing, and pharmacy operations. The project demonstrates a real-world microservices architecture using **Spring Boot**, **Eureka Server**, **MySQL**, **DTOs**, **Spring Security**, and configuration management. The project is also designed to be tested with **Postman**.

---

## **Project Structure**

The project consists of the following microservices:

1. **Eureka Server**  
   - Service discovery server for all microservices  
   - Enables dynamic registration and discovery of services  

2. **User Service**  
   - Manages patient/user registration and authentication  
   - Implements basic Spring Security for login  

3. **Appointment Service**  
   - Handles creation, updating, and cancellation of appointments  
   - Uses DTOs for data transfer  

4. **Pharmacy Service**  
   - Manages medicines and prescriptions  
   - Supports CRUD operations  

5. **Billing Service**  
   - Handles billing and payments for services  
   - Integrates with appointment and pharmacy services  

---

## **Technology Stack**

- **Backend:** Java, Spring Boot  
- **Service Discovery:** Eureka Server  
- **Database:** MySQL  
- **Security:** Spring Security (Basic Auth)  
- **DTO:** Data Transfer Objects for safe communication between services  
- **Testing:** Postman  
- **Configuration:** Spring Boot Config / Application Properties  

---
###Port
eureka_server= 8761
user_service= 8081
appointment_service= 8082
pharmacy_service= 8083
billing_service= 8084

## **Features**

- Microservice architecture with service discovery  
- Secure user authentication using Spring Security  
- Centralized configuration management  
- CRUD operations for users, appointments, medicines, and billing  
- Separation of concerns: Each service is independent  
- Can be tested via Postman with pre-configured endpoints  

---

###Database
Schema= mediserve_user
user_service= users as table
appointment_service= appointments as table
pharmacy_service= medicines and prescription as tables
billing_service= bills as table

### Prerequisites

- Java JDK 17 or above  
- Maven 3.5.6  
- MySQL Database  
- IDE (STS / Eclipse / IntelliJ IDEA)  
- Postman (for testing APIs)  


