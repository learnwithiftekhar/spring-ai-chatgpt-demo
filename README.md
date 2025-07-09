# Spring AI Demo - Trip Planner

A simple demonstration application that shows how to integrate ChatGPT API with Spring AI framework. This application provides an AI-powered trip planning service that generates weekend trip itineraries based on user preferences.

## Features

- AI-powered trip planning using OpenAI's GPT-4.1 model
- RESTful API for trip planning requests
- Customizable trip parameters (destination, date, budget, travel party size)
- Built with Spring Boot 3.5.3 and Spring AI 1.0.0

## Prerequisites

Before running this application, ensure you have:

- **Java 21** or higher installed
- **Maven 3.6+** for building the project
- **OpenAI API Key** - You'll need a valid OpenAI API key to use the ChatGPT integration

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/learnwithiftekhar/spring-ai-chatgpt-demo.git
cd spring-ai-chatgpt-demo
```

### 2. Set Up OpenAI API Key

You need to set your OpenAI API key as an environment variable. You can do this in several ways:

#### Option A: Environment Variable
```bash
export OPEN_AI_KEY=your_openai_api_key_here
```

#### Option B: System Properties
```bash
java -DOPEN_AI_KEY=your_openai_api_key_here -jar target/spring-ai-demo-0.0.1-SNAPSHOT.jar
```

#### Option C: IDE Configuration
If using an IDE, add `OPEN_AI_KEY=your_openai_api_key_here` to your run configuration environment variables.

### 3. Build the Application

```bash
mvn clean compile
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/spring-ai-demo-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## API Usage

### Trip Planning Endpoint

**Endpoint:** `GET /trip-planner`

**Content-Type:** `application/json`

**Request Body:**
```json
{
    "destination": "Paris, France",
    "date": "2024-03-15",
    "budget": 1500,
    "numOfAdults": 2,
    "numOfChildren": 1
}
```

**Response:** Plain text containing the AI-generated trip itinerary

### Example Usage

#### Using cURL:
```bash
curl -X GET http://localhost:8080/trip-planner \
  -H "Content-Type: application/json" \
  -d '{
    "destination": "New York City",
    "date": "2024-04-20",
    "budget": 2000,
    "numOfAdults": 2,
    "numOfChildren": 0
  }'
```

#### Using Postman:
1. Set method to `GET`
2. URL: `http://localhost:8080/trip-planner`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON):
```json
{
    "destination": "Tokyo, Japan",
    "date": "2024-05-10",
    "budget": 3000,
    "numOfAdults": 1,
    "numOfChildren": 0
}
```

## Configuration

The application uses the following configuration in `application.properties`:

```properties
spring.application.name=spring-ai-demo
spring.ai.openai.api-key=${OPEN_AI_KEY}
spring.ai.openai.chat.options.model=gpt-4.1
```

### Configuration Options

- `spring.ai.openai.api-key`: Your OpenAI API key (set via environment variable)
- `spring.ai.openai.chat.options.model`: The OpenAI model to use (currently set to gpt-4.1)

You can modify the model by changing the value in `application.properties`. Available models include:
- `gpt-4.1`
- `gpt-4`
- `gpt-3.5-turbo`

## Project Structure

```
src/
├── main/
│   ├── java/com/learnwithiftekhar/spring_ai_demo/
│   │   ├── SpringAiDemoApplication.java    # Main Spring Boot application class
│   │   ├── TripPlannerController.java      # REST controller for trip planning
│   │   ├── AIService.java                  # Service layer for AI interactions
│   │   └── PlanModel.java                  # Data model for trip planning requests
│   └── resources/
│       └── application.properties          # Application configuration
└── test/
    └── java/com/learnwithiftekhar/spring_ai_demo/
        └── SpringAiDemoApplicationTests.java
```

### Key Components

#### 1. `SpringAiDemoApplication.java`
The main Spring Boot application class that bootstraps the application.

#### 2. `TripPlannerController.java`
REST controller that exposes the `/trip-planner` endpoint. It:
- Accepts trip planning requests
- Constructs AI prompts based on user input
- Returns AI-generated trip itineraries

#### 3. `AIService.java`
Service layer that wraps the Spring AI ChatClient. It provides:
- Simple abstraction for AI interactions
- Chat functionality using OpenAI's API

#### 4. `PlanModel.java`
Data model representing trip planning parameters:
- `destination`: Trip destination
- `date`: Trip start date
- `budget`: Total budget in USD
- `numOfAdults`: Number of adults (default: 1)
- `numOfChildren`: Number of children (default: 0)

## Dependencies

The project uses the following key dependencies:

- **Spring Boot 3.5.3**: Core framework
- **Spring AI 1.0.0**: AI integration framework
- **spring-ai-starter-model-openai**: OpenAI integration starter
- **spring-boot-starter-web**: Web and REST capabilities

## Troubleshooting

### Common Issues

1. **Missing API Key Error**
   - Ensure `OPEN_AI_KEY` environment variable is set
   - Verify the API key is valid and has sufficient credits

2. **Connection Issues**
   - Check internet connectivity
   - Verify OpenAI API is accessible from your network

3. **Model Not Found**
   - Ensure the model specified in `application.properties` is available
   - Check OpenAI documentation for available models

### Logs

Enable debug logging by adding to `application.properties`:
```properties
logging.level.org.springframework.ai=DEBUG
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is for demonstration purposes. Please refer to OpenAI's terms of service for API usage guidelines.

## Additional Resources

- [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/)
- [OpenAI API Documentation](https://platform.openai.com/docs)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
