# PortfolioTracker

![CI](https://github.com/t0ny777/PortfolioTracker/actions/workflows/ci.yml/badge.svg)

Web application for tracking and analyzing your cryptocurrency portfolio.  
Built with **Java 17** and **Spring Boot 3**, it provides a clean REST API and a simple web interface to monitor your investments, calculate profit/loss, and visualize asset allocation.

---

## Screenshots

- [Project Structure](screenshots/project-structure.png)
- [Docker Build](screenshots/docker-build-run.png)
- [Docker Localhost](screenshots/docker-build-run_localhost.png)
- [Maven Build](screenshots/maven-build-run.png)
- [Introduction to PortfolioTracker](screenshots/intro_to_portfoliotracker.png)
- [Portfolio Interface](screenshots/portfolio-interface.png)
- [Crypto Market Interface](screenshots/crypto-market-interface.png)
- [Asset Add Interface](screenshots/asset-add-interface.png)
- [Asset Remove Interface](screenshots/asset-remove-interface.png)

---

## 🚀 Features

- **Portfolio Management**: Full CRUD operations for your crypto assets.  
- **Automatic Calculations**: Real-time computation of:  
  - Total invested amount  
  - Current portfolio value  
  - Profit/Loss (absolute and percentage ROI)  
- **Data Aggregation**: Assets are grouped by type for a summarized view.  
- **Data Visualization**: Interactive pie chart for asset allocation.  
- **REST API**: Well-structured API for programmatic access.  
- **In-Memory Database**: Uses H2 database (auto-configured, no setup).  
- **Comprehensive Testing**: Unit and integration tests included.  
- **Containerization**: Fully Dockerized for easy deployment.  

---

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3.2.5  
- **Web**: Spring Web, Spring Validation  
- **Persistence**: Spring Data JPA, H2 Database  
- **API Documentation**: Springdoc OpenAPI  
- **Utilities**: Project Lombok  
- **Testing**: JUnit 5, Mockito, Spring Boot Test  
- **Containerization**: Docker, Docker Compose  
- **Build Tool**: Apache Maven  
- **CI/CD**: GitHub Actions  

---

## ⚡ Quick Start

### Prerequisites
- **Docker** and **Docker Compose** (Recommended)  
- Or **Java 17** and **Maven 3.9+**

### Method 1: Run with Docker (Recommended)

```bash
# Clone the repository
git clone https://github.com/your-username/portfolio-tracker.git
cd portfolio-tracker

# Build and start the container
docker-compose up --build
👉 App will be available at: http://localhost:8080
```

### Method 2: Run Locally with Maven

```bash
# Clone the repository
git clone https://github.com/your-username/portfolio-tracker.git
cd portfolio-tracker

# Build and run
mvn clean install
mvn spring-boot:run


👉 App will be available at: http://localhost:8070

```

### Method 3: Run from NetBeans IDE

```bash
Open the project in NetBeans

Right-click the project → Run

App will be available at: http://localhost:8070
```

## 📖 API Documentation

Once the application is running, Swagger UI is available at:

👉 http://localhost:8070/swagger-ui.html

Example API Requests

Get Portfolio Summary
```bash
GET /api/portfolio
```

Add a New Asset

```bash
POST /api/portfolio
Content-Type: application/json
```
```bash
{
  "assetId": "BTC",
  "assetName": "Bitcoin",
  "assetSymbol": "BTC",
  "quantity": 0.01,
  "purchasePrice": 30000,
  "purchaseDate": "2024-05-01"
}
```

Delete an Asset

```bash
DELETE /api/portfolio/BTC
```

## ✅ Testing

The project is covered with both unit and integration tests.

To run tests:

```bash
mvn test
```

The GitHub Actions CI/CD pipeline automatically runs tests on every push.

---

## 📁 Project Structure

PortfolioTracker1
├── src/
│ ├── main/
│ │ ├── java/com/nurda/spring/
│ │ │ ├── controller/ # REST controllers (API layer)
│ │ │ ├── dto/ # Data Transfer Objects
│ │ │ ├── model/ # JPA entities and domain models
│ │ │ ├── repo/ # Data access layer (Spring Data JPA repositories)
│ │ │ └── PortfolioTracker1.java # Main Spring Boot application class
│ │ └── resources/
│ │ ├── static/ # Static files (HTML, JS)
│ │ └── application.properties # App configuration
│ └── test/ # Unit and integration tests
└── pom.xml # Maven build file

---

## 📝 License

This project is licensed for educational and portfolio purposes.

---

## 👨‍💻 Author

Developed as a pet project to showcase backend development skills with Java and Spring Boot.
Purpose: Demonstration of modern Java backend practices for my portfolio, aiming to secure a Junior Java Backend Developer position.
