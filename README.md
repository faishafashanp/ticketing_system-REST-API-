# Ticketing System REST API

A scalable backend RESTful API for managing event ticketing, built using Spring Boot.  
This system supports authentication, role-based access control, and ticket booking management.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User registration and login  
- JWT-based authentication  
- Role-based access control (Admin & Customer)  

### 🎟️ Ticket Management
- Create, update, and delete tickets  
- Retrieve tickets by ID or event  
- Ticket booking with validation rules  
- Ticket expiration handling  

### 📅 Event Management
- Create and manage events (Admin only)  
- View available events (Customer)  

---

## 🛠️ Tech Stack

- Java  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- MySQL  
- Maven  
- Postman  

---

## 📌 API Endpoints

### Authentication
- `POST /auth/register` → Register new user  
- `POST /auth/login` → Login user  

### Event Management
- `GET /events` → Get all events  
- `POST /events` → Create event (Admin)  
- `PUT /events/{id}` → Update event (Admin)  
- `DELETE /events/{id}` → Delete event (Admin)  

### Ticket Management
- `POST /tickets` → Book ticket  
- `GET /tickets` → Get all tickets  
- `GET /tickets/{id}` → Get ticket by ID  
- `PUT /tickets/{id}` → Update ticket  
- `DELETE /tickets/{id}` → Cancel ticket  

---

## ⚙️ Business Rules

- Ticket booking is limited per event/session  
- Only Admin can manage events  
- Customers can only access their own tickets  
- Tickets may expire after a defined period  

---

# Author

Faisha Fasha Natasya Putri

GitHub = https://github.com/faishafashanp

Linkedin = www.linkedin.com/in/faishafasha
