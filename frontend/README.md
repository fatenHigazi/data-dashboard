# Real-Time Sensor Data Dashboard

##  Overview
This full-stack application provides real-time temperature and humidity data using **Java Spring Boot (Backend)** and **Angular (Frontend)**. The system simulates a sensor using a **UART connection**, processes the data, and displays it on an interactive dashboard.

##  Features
 **Simulated UART Communication** – A backend service generates real-time sensor data.
 **Data Validation & Storage** – The backend processes and stores valid readings in a relational database.
 **REST API** – Secure endpoints to fetch sensor data.
 **Real-Time Dashboard** – The frontend updates live without refreshing the page.
 **Charts & Data Trends** – Visual representation of temperature and humidity.
 **Flyway Database Migration** – Automated schema versioning.
 **Comprehensive Testing** – Unit tests for both frontend and backend components.

---

##  Architecture
This project follows a **modern full-stack design**:

### **1️ Backend (Java Spring Boot)**
- **Spring Boot** for rapid backend development.
- **Simulated UART Communication** – Generates real-time data in `temperature,humidity` format.
- **Data Validation** – Ensures values are within reasonable thresholds.
- **Database** – Stores readings using **PostgreSQL / H2 / MySQL**.
- **REST API** – Provides endpoints for frontend data access.
- **Unit Tests** – JUnit for backend testing.

### **2️ Frontend (Angular)**
- **Angular CLI** for project management.
- **Component-Based UI** – Displays real-time temperature and humidity.
- **Live Updates** – Uses **WebSockets / Long Polling**.
- **Charts** – Uses **Chart.js** or **ng2-charts** for trends.
- **Unit Tests** – Jasmine + Karma for UI testing.

---

## 🛠️ Setup & Installation

### **1️ Prerequisites**
Ensure you have the following installed:
- **Java 17+**
- **Node.js + npm**
- **Angular CLI**
- **PostgreSQL 
- **Docker** (Optional, for database)

### **2️ Backend Setup**
1. Clone the repository:
   ```bash
   git clone https://github.com/fatenhigazi/data-dashboard.git
   cd data-dashboard/backend
   ```
2. Configure **application.properties**:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5433/sensordb
   spring.datasource.username=postgres
   spring.datasource.password=password
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### **3️ Frontend Setup**
1. Navigate to the frontend directory:
   ```bash
   cd ../frontend/sensor-dashboard
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular app:
   ```bash
   ng serve --open
   ```

---

##  REST API Endpoints

| Method | Endpoint | Description |
|--------|------------|----------------------------|
| `GET`  | `/api/sensor` | Fetch all sensor readings |
| `GET`  | `/api/sensor/latest` | Get the latest sensor reading |


 **Example Response:**
```json
{
  "timestamp": "2025-03-10T12:00:00Z",
  "temperature": 25.3,
  "humidity": 60.1
}
```

---

##  UART Simulation
The backend **simulates a UART connection** that generates sensor readings every **2 seconds**.
```plaintext
25.5,60.2
26.1,59.8
```

This data is processed and stored in the database.

---

##  Database & Flyway Migration
This project uses **Flyway** for automatic database schema migration.

### **1️ Configure Flyway**
Flyway scripts are stored in `src/main/resources/db/migration/`.
Example **V1__Create_Sensor_Table.sql**:
```sql
CREATE TABLE sensor_data (
    id SERIAL PRIMARY KEY,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    temperature DOUBLE PRECISION,
    humidity DOUBLE PRECISION
);
```
When the backend starts, **Flyway automatically applies migrations**.

---

## Testing

### **1️ Backend Tests (JUnit + Mockito)**
Run backend tests:
```bash
mvn test
```
✔**Test Cases**:
- Validates simulated sensor data.
- Checks API responses.
- Ensures database integrity.

### **2️ Frontend Tests (Jasmine + Karma)**
Run frontend tests:
```bash
ng test
```
✔**Test Cases**:
- Verifies UI updates in real-time.
- Ensures API data is displayed correctly.

### **3️ End-to-End Tests (Cypress)**
Run Cypress E2E tests:
```bash
npx cypress open
```
✔**Test Cases**:
- Loads dashboard correctly.
- Displays updated sensor values.
- Handles edge cases (no data, errors).

---

##  Summary
This project integrates **real-time data processing, validation, and visualization** into a **robust full-stack application**. The **backend** handles simulated UART data, stores it in a database, and serves it through a **REST API**, while the **frontend** provides a dynamic, real-time dashboard.



