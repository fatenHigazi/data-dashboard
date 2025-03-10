

###  **Data Dashboard**  
**Real-time Sensor Data Monitoring (Angular + Spring Boot)**  

Welcome to the **Data Dashboard**! This project is a **full-stack web application** that visualizes real-time sensor data using an **Angular frontend** and a **Spring Boot backend**. It simulates sensor readings (temperature & humidity) and displays them dynamically on a dashboard.

---

##  **Project Overview**
The **Data Dashboard** is designed to:
Simulate a virtual sensor that sends **temperature & humidity** data.  
Process and store the data in a **Spring Boot backend** with a database.  
Expose the data via **RESTful API endpoints**.  
Provide a clean and interactive **Angular frontend** to visualize real-time updates.  

---

##  **Tech Stack**
| Layer | Technology |
|--------|------------|
| **Frontend** | Angular, TypeScript, HTML, CSS |
| **Backend** | Java, Spring Boot, REST API |
| **Database** | PostgreSQL (or H2 for testing) |
| **Communication** | HTTP (REST API), WebSockets (optional) |

---

##  **Getting Started**

### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/fatenHigazi/data-dashboard.git
cd data-dashboard
```

---

### **2️⃣ Backend (Spring Boot) Setup**
#### **Run Locally with Java & Maven**
Make sure you have **Java 17+** and **Maven** installed.

```bash
cd backend
mvn spring-boot:run
```
This will:
- Start the Spring Boot backend on **`http://localhost:8080`**.
- Simulate sensor data via a **mock UART interface**.
- Expose sensor data through a **REST API**.
- Automatically run Flyway database migrations on startup.


#### **Check API Endpoint**
Once running, test the API:
```bash
curl http://localhost:8080/api/sensor
```

---
#### **Database Setup & Flyway Migrations**
This project uses Flyway to manage database migrations. Flyway ensures that your database schema is always up to date.

1️⃣ Configure Your Database
By default, the project uses PostgreSQL. Make sure you have PostgreSQL installed and running.

Set up a new database
sql
Copy
Edit
CREATE DATABASE sensor_data;
Update application.properties (or application.yml)
Modify backend/src/main/resources/application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://localhost:5432/sensor_data
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Flyway settings
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
2️⃣ Run Flyway Migrations
Flyway migrations will automatically run when the backend starts.
If you need to manually apply migrations, run:

bash
Copy
Edit
mvn flyway:migrate
This will: 
- Apply any new SQL migration files in backend/src/main/resources/db/migration/.
- Keep track of migration history in the flyway_schema_history table.

3️⃣ Check Applied Migrations
To see which migrations have been applied:

bash
Copy
Edit
mvn flyway:info
If you need to rollback the last migration:

bash
Copy
Edit
mvn flyway:undo

---
### **3️⃣ Frontend (Angular) Setup**
#### **Run Locally with Node.js & Angular CLI**
Ensure you have installed **Node.js 18+** and **Angular CLI**.

```bash
cd frontend
npm install
ng serve
```
This will:
- Start the Angular frontend on **`http://localhost:4200/dashboard`**.
- Automatically fetch sensor data from the backend.
- Update the UI in real-time using `*ngFor`.

---

##  **API Endpoints**
| Method | Endpoint | Description |
|--------|----------|-------------|
| **GET** | `/api/sensor` | Fetch all sensor readings |
| **GET** | `/api/sensor/latest` | Get the latest sensor reading |

---

##  **Frontend Features**
 **Live Data Updates** – Displays sensor data in real-time.  
 **Clean & Responsive UI** – User-friendly design built with Angular.  
 **Interactive Charts** – Uses `Chart.js` for data visualization.  
 **Error Handling** – Handles API failures gracefully.  

---
###  ** Frontend & Backend Testing Instructions**  

This includes **unit tests (Jasmine + Karma for frontend, JUnit for backend)** and **end-to-end (E2E) tests (Cypress for frontend, Postman for backend API testing)**.

---

#  **Data Dashboard**
**Real-time Sensor Data Monitoring (Angular + Spring Boot)**  

 A full-stack web application that visualizes real-time sensor data using an **Angular frontend** and a **Spring Boot backend**. It simulates sensor readings (temperature & humidity) and displays them dynamically on a dashboard.

---

##  **Testing Guide**  
This project includes both **frontend and backend testing**:

| Test Type  | Tool | Run Command |
|------------|------|-------------|
| **Frontend Unit Tests** | Jasmine + Karma | `ng test` |
| **Frontend E2E Tests (GUI Mode)** | Cypress | `npx cypress open` |
| **Frontend E2E Tests (Headless Mode)** | Cypress | `npx cypress run` |
| **Backend Unit Tests** | JUnit + Spring Boot | `mvn test` |
| **Backend API Tests** | Postman / cURL | Manually test endpoints |

---

##  **1️⃣ Frontend Testing (Angular)**
This project includes **unit tests** (Jasmine + Karma) and **end-to-end (E2E) tests** (Cypress).

###  **Run Frontend Unit Tests**
To test individual components and services, run:

```bash
ng test
```
This will:
- Execute **unit tests** using **Jasmine & Karma**.
- Display test results in the terminal and browser.
- Automatically watch for changes and re-run tests.

#### **Example Unit Test (`dashboard.component.spec.ts`)**
```typescript
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DashboardComponent } from './dashboard.component';
import { SensorService } from '../sensor.service';
import { of } from 'rxjs';
import { CommonModule } from '@angular/common';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let mockSensorService: jasmine.SpyObj<SensorService>;

  beforeEach(async () => {
    mockSensorService = jasmine.createSpyObj('SensorService', ['getSensorData']);
    
    await TestBed.configureTestingModule({
      imports: [CommonModule],
      providers: [{ provide: SensorService, useValue: mockSensorService }],
      declarations: [DashboardComponent]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch sensor data on init', () => {
    const mockData = [{ temperature: 25, humidity: 60, timestamp: '2024-03-09T08:00:00Z' }];
    mockSensorService.getSensorData.and.returnValue(of(mockData));

    component.ngOnInit();

    expect(component.sensorData).toEqual(mockData);
  });
});
```

---

###  **Run Frontend End-to-End (E2E) Tests**
End-to-end tests simulate user actions (e.g., visiting the dashboard and checking real-time data).

#### **Step 1: Install Cypress**
```bash
npm install cypress --save-dev
```

#### **Step 2: Open Cypress (GUI Mode)**
```bash
npx cypress open
```
This opens the Cypress Test Runner.

#### **Step 3: Run All E2E Tests**
To run all tests in **headless mode** (without opening a browser):
```bash
npx cypress run
```

#### **Example Cypress E2E Test (`cypress/e2e/dashboard.spec.js`)**
```javascript
describe('Data Dashboard', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200');
  });

  it('should display sensor data', () => {
    cy.intercept('GET', 'http://localhost:8080/api/sensor', [
      { temperature: 24, humidity: 50, timestamp: '2024-03-09T08:00:05Z' }
    ]).as('getSensorData');

    cy.wait('@getSensorData');
    
    cy.contains('Temperature: 24°C');
    cy.contains('Humidity: 50%');
  });
});
```

---

## **2️⃣ Backend Testing (Spring Boot)**
The backend includes **unit tests (JUnit + Mockito)** and **API tests (Postman or cURL)**.

### **Run Backend Unit Tests**
To run all backend tests:

```bash
mvn test
```

This will:
- Execute **JUnit tests** for services, controllers, and repositories.
- Check if API endpoints return the expected responses.

#### **Example Unit Test (`SensorServiceTest.java`)**
```java
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SensorServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private SensorService sensorService;

    @Test
    public void testFetchLatestSensorData() {
        SensorData mockData = new SensorData(25.5, 60.2);
        when(sensorRepository.findTopByOrderByTimestampDesc()).thenReturn(mockData);

        SensorData result = sensorService.getLatestSensorData();

        assertEquals(25.5, result.getTemperature());
        assertEquals(60.2, result.getHumidity());
    }
}
```

---

### ✅ **Run API Tests (Postman / cURL)**
You can manually test API endpoints using **Postman** or **cURL**.

#### **Example: Test `GET /api/sensor` with cURL**
```bash
curl -X GET http://localhost:8080/api/sensor
```

#### **Example: Test `POST /api/sensor` with cURL**
```bash
curl -X POST http://localhost:8080/api/sensor \
     -H "Content-Type: application/json" \
     -d '{"temperature": 28.4, "humidity": 55.1}'
```

---

## 🎯 **Final Summary**
| Test Type  | Tool | Run Command |
|------------|------|-------------|
| **Frontend Unit Tests** | Jasmine + Karma | `ng test` |
| **Frontend E2E Tests (GUI Mode)** | Cypress | `npx cypress open` |
| **Frontend E2E Tests (Headless Mode)** | Cypress | `npx cypress run` |
| **Backend Unit Tests** | JUnit + Spring Boot | `mvn test` |
| **Backend API Tests** | Postman / cURL | Manually test endpoints |

---

## **License**
This project is **open-source** and available under the **MIT License**. Feel free to use, modify, and share! 

---

## **Final Thoughts**
Thanks for checking out the **Data Dashboard**! If you found this project helpful, please **star this repo** and share your feedback. 

---

###  by [Faten Higazi](https://github.com/fatenHigazi)**  

---
#   d a t a - d a s h b o a r d  
 