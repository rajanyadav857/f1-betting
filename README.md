# F1 Betting Application

A Spring Boot application for managing Formula 1 betting, including user management, event fetching, bet placement, and outcome processing.

## Features

- Fetch and display real-time F1 event data from the OpenF1 API
- List available drivers for each event with dynamic odds
- Place bets on drivers for specific F1 events
- Simulate and process event outcomes, automatically updating bet statuses and user balances
- Comprehensive logging to both file and console for monitoring and debugging
- RESTful API endpoints for all major operations (events, bets, outcomes)
- Easy integration with API testing tools like Postman

## Tech Stack

- Java
- Spring Boot
- MySQL
- Maven

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/rajanyadav857/f1-betting.git
   cd f1-betting

## How to Run
1. Import as a Maven project in your IDE.
2. Create a MySQL database named `f1_betting`.
3. Run the `schema.sql` file to create DB & tables.
4. Update `application.properties` with your DB credentials.
5. Run the Spring Boot application.
6. Import the Postman collection `F1_Betting_API.postman_collection.json` to test the API.

## API Endpoints
- GET `/api/events`
- POST `/api/bet`
- POST `/api/simulate`