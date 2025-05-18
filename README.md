# 🍹 Juice Production Planning System

A full-stack web application for planning and managing juice production processes. The system supports recipe management, ingredient inventory, production planning, and real-time tracking of active productions. It also includes user authentication and role-based authorization.

## 📋 Features

- **Recipe and Juice Composition Tracking**  
  Define recipes for different juices, including ingredients, quantities, price, and description.

- **Ingredient Inventory Management**  
  Automatically update inventory based on recipe usage and juice production data.

- **Production Planning**  
  Select a juice and an available machine to plan production, while ensuring sufficient ingredient stock.

- **Production Execution Monitoring**  
  Track initiated productions, their status (active/inactive), start date, and the user who initiated it.

- **Security**  
  - JWT-based user authentication
  - Role-based authorization (Admin / Customer)
  - Email verification on registration to prevent unauthorized access

## 👥 User Roles

- **Administrator**
  - View and manage all productions
  - Update production statuses
- **Customer**
  - Initiate production requests
  - View their own production history

## 🛠️ Tech Stack

### Backend
- **Java Spring Boot**
  - RESTful API
  - JPA (Hibernate)
  - Spring Security (JWT, Email Verification)
  - Integrated with MySQL

### Frontend
- **React.js**
  - Component-based UI
  - Axios for API communication
  - JSX + Conditional rendering

### Database
- **MySQL**
  - Relational data model based on class diagrams
  - Direct mapping using `@Entity`, `@Table`, `@Id`, etc.

## 🔐 Security and Authentication

- **JWT Token** generated on successful login
- **Token validation** on each secured endpoint
- **Email verification** required after registration
