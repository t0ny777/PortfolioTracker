# PortfolioTracker

![CI](https://github.com/t0ny777/PortfolioTracker/actions/workflows/ci.yml/badge.svg)

Web application for tracking and analyzing your cryptocurrency portfolio.  
Built with **Java 17** and **Spring Boot 3**, it provides a clean REST API and a simple web interface to monitor your investments, calculate profit/loss, and visualize asset allocation.

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
