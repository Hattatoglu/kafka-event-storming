# Kafka Event Storming Spring Java Project

## Overview
This project is a Spring-based Java application leveraging Kafka for event-driven architecture. The core functionality focuses on message exchange through Kafka, providing a simplified infrastructure setup and implementation.

## General Features
1. **Event Storming with Kafka**: The project is designed to simulate event storming using Kafka.
2. **Infrastructure Requirements**:
    - **Zookeeper**: Required to coordinate Kafka brokers.
    - **Kafka**: The message broker for event processing.
    - **Kafka UI**: An optional tool for managing and monitoring Kafka topics, brokers, and consumers.
3. **Infrastructure Setup**:
    - Docker Compose files are located in the `application-infrastructure` directory of the project.
    - In the compose files, replace `<<local-machine-ip>>` with the IP address of the machine running Docker.
4. **Startup Order**:
    - Start the Zookeeper container first.
    - Wait 30-60 seconds, then start the Kafka container.
    - Kafka UI can be started afterward as needed.
5. **Accessing Kafka UI**:
    - After starting Kafka UI, access it via a web browser at `http://localhost:9091`.
    - Use it to monitor Kafka brokers, topics, and consumers.

## Project Features (Version 1.0)
1. **Kafka-Centric Architecture**:
    - The project is designed solely for Kafka-based message exchange.
    - No additional infrastructure is used.
2. **Service Logic**:
    - The project focuses on message publishing and consumption, with no other service-specific logic implemented.
3. **Microservices**:
    - **Service Message Creator**:
        - Endpoint: `POST /api/v1/contents` at `http://localhost:8500`
        - Accepts a JSON payload containing `username` and `content`.
        - Example JSON payload:
           ```json
           {
               "username": "test",
               "content": "message test"
           }
        - Generates a unique link and publishes a `ContentCreated` event with header and body details to the Kafka topic `service-content-topic`.
    - **Service Message Reader**:
        - Listens to the Kafka topic `service-content-topic`.
        - Processes incoming messages, converts them to events, and logs the details.

## Getting Started
### Prerequisites
- Docker and Docker Compose installed on your machine.
- Ensure the IP address for `<<local-machine-ip>>` is correctly configured in the Compose files.

### Running the Infrastructure
1. Navigate to the `application-infrastructure` directory.
   ```bash
   cd ..\<<your directory>>\application-infrastructure
   ```
2. Start the Zookeeper container:
   ```bash
   docker-compose -f zookeeper.yml up -d
   ```
3. Wait 30-60 seconds, then start the Kafka container:
   ```bash
   docker-compose -f kafka.yml up -d
   ```
4. Optionally, start Kafka UI:
   ```bash
   docker-compose -f kafkaui.yml up -d
   ```

### Accessing Kafka UI
Open a browser and navigate to `http://localhost:9091` to manage Kafka topics and consumers.

### Running the Microservices
1. Start the `service-message-creator` microservice.
2. Start the `service-message-reader` microservice.

## Contribution
Feel free to contribute to this project by submitting issues or pull requests.

## License
This project is licensed under the MIT License.

