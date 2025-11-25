# weatherapi

[![Build Status](https://img.shields.io/github/actions/workflow/status/jenspapenhagen/weatherapi/ci.yml)](https://github.com/jenspapenhagen/weatherapi/actions)
![GitHub last commit](https://img.shields.io/github/last-commit/jenspapenhagen/weatherapi)
![GitHub repo size](https://img.shields.io/github/repo-size/jenspapenhagen/weatherapi)
![GitHub license](https://img.shields.io/github/license/jenspapenhagen/weatherapi)
![Java Version](https://img.shields.io/badge/java-25-blue)

A simple weather summary service.

## Overview

This project provides a service to fetch and present weather summaries for given locations.

## Features

- Fetch current weather data for a specified location
- Return a concise weather summary (temperature, conditions, and more)

## Getting Started

### Prerequisites

- A valid API key from a openWeather
- A valid API key from OpenAI
- Java 25
- Maven
- Docker / Docker Compose (optional)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/jenspapenhagen/weatherapi.git
   cd weatherapi
   ```
2. Set up your environment variables (example: `.env.example` if provided).
3. Build with Maven:
   ```bash
   ./mvnw clean install
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Or run using Docker Compose:
   ```bash
   docker compose up --build
   ```

## Usage

Query the weather summary endpoint:

```
GET /weather?location=<LOCATION>
```

Example:
```http
GET http://localhost:8080/weather?location=Berlin
```

Example Response:
```json
Sunny and warm, bring sunglasses.
```

## Configuration

Environment variables:

- `OPENAI_API_KEY`
- `OPENWEATHER_API_KEY`

## License

MIT License – see [LICENSE](LICENSE) for details