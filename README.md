# **User Service Template (Jakarta EE 10)**

A **lightweight** and **ready-to-use** user authentication service built with **Jakarta EE 10**. Designed as a **starter template** for future projects requiring **JWT-based authentication and role-based authorization**.

---

## 🚀 Features

✔️ **User Authentication** – 🔑 Secure login with JWT token generation  
✔️ **Role-Based Access Control** – 🔐 Restrict access using `ADMIN` and `USER` roles  
✔️ **Security Filters** – 🛡️ Implements authentication & authorization filters  
✔️ **Database Integration** – 🗄️ Uses MySQL with Hibernate JPA  
✔️ **Configuration Management** – ⚙️ Loads settings from `config.properties`  
✔️ **Logging** – 📜 Log4j for structured application logging

---

## 🛠 Tech Stack

- **Jakarta EE 10** (JAX-RS, CDI, JPA)
- **JWT Authentication** (Custom security filters)
- **MySQL & Hibernate ORM**
- **Log4j** for logging
- **Bcrypt** for password hashing
- **Open Liberty** as the application server

---

### **4️⃣ API Endpoints**

| Method  | Endpoint        | Description               | Auth Required |
|---------|----------------|---------------------------|------------|
|  **POST**  | `/auth/register` |  Register a new user     |  No       |
|  **POST**  | `/auth/login`    |  Authenticate & return JWT |  No       |
|  **GET**   | `/users/{id}`    |  Get user by ID          | Yes (Admin) |
|  **DELETE** | `/users/{id}`    |  Delete user              | Yes (Admin) |

💡 **Use Postman or `curl` to test the endpoints.**
