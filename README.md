# 🏨 Hotel Reservation System

A full-stack web application to manage hotel room bookings.  
Customers can view and book rooms, while the admin can manage bookings and vacate rooms.

---

## 🚀 Features

### 👤 Customer
- View all rooms (Available & Booked)
- Book a room
- Real-time room status updates

### 👨‍💼 Admin
- View all room details
- See customer name & phone
- Vacate rooms (make them available again)

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot
- **Frontend:** HTML, CSS, Bootstrap, JavaScript
- **Database:** MySQL
- **Tools:** Eclipse, Postman, GitHub

---

## 📂 Project Structure
com.hotel
├── controller
├── service
├── model
├── repo

---

## ▶️ How to Run

1. Run Spring Boot Application  
2. Open browser  
3. Go to:http://localhost:8080/index.html

---

## 🔗 API Endpoints

### Rooms
- `GET /rooms/all` → Get all rooms  
- `GET /rooms/available` → Get available rooms  
- `PUT /rooms/vacate/{id}` → Vacate room  

### Booking
- `POST /reservations/book/{roomId}` → Book room  

---

## 📸 Screens (Optional)
- Rooms Page  
- Booking Page  
- Admin Dashboard  

---

## 💡 Future Improvements
- User authentication (login/signup)
- Booking history
- Payment integration
- Search & filter rooms

---

## 👨‍💻 Author
Your Name

---

## ⭐ GitHub
If you like this project, give it a star ⭐
