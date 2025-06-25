# 🩺 Medical Appointments Mobile App

This mobile application helps users manage **medical appointments** efficiently. It supports two user roles: **Patient** and **Doctor**, each with a personalized experience.

---

## 👤 Patient Features

- 🔐 **Register & Login**  
  Users can register as a Patient or Doctor. Authentication is simulated using local storage (SharedPreferences).

- 📅 **View My Appointments**  
  Patients can view only their scheduled appointments in a user-friendly list.

- ➕ **Add Appointment**  
  Patients can schedule a new appointment by selecting:
  - **Medical Specialty**
  - **Doctor**, filtered based on selected specialty
  - **Category**: _Surgery, Video Consultation, Regular Check-up, Follow-up_
  - **Date & Time**, with 30-minute intervals and availability validation
  - **Optional Description**

---

## 🩻 Doctor Features

- 🏥 **Doctor Dashboard**  
  Doctors can view only their own appointments, with details such as:
  - Patient's **Name**, **Surname**, and **Age**
  - Appointment **Category**
  - Appointment **Date & Time**

- 🔽🔼 **Sort Appointments by Date**  
  Doctors can sort their appointments using **SVG arrow buttons** for ascending/descending order.

---

## 🧠 Technical Highlights

- Built with **Java (Android)** and **XML layouts**
- Authentication simulated using **SharedPreferences**
- Dynamic form behavior with **filtered dropdowns**
- Separate navigation flows for **Patients** and **Doctors**
- Clean UI and responsive user experience

---

## 📌 Notes

- This is a student project for educational purposes.
- All backend logic is simulated (no real API integration).

---

## 📸 Screenshots

_(Insert screenshots here if available)_

---

## 🚀 Future Improvements

- Firebase or REST API integration for real backend support
- Push notifications for upcoming appointments
- Appointment editing & cancellation features

---

