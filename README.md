# Spring Boot Microservices Project

This project is a collection of 8 microservices built using Spring Boot. It demonstrates a full microservices architecture including service discovery, configuration management, API gateway, asynchronous messaging, and distributed tracing. The project is containerized using Docker and orchestrated with Docker Compose.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Services Overview](#services-overview)
- [Installation and Setup](#installation-and-setup)
- [Running the Project](#running-the-project)
- [Usage](#usage)

## Overview

This project consists of eight different microservices:

- **Customer Service**: Manages customer information (data stored in MongoDB).
- **Product Service**: Manages product-related data (data stored in PostgreSQL).
- **Notification Service**: Sends notifications to users and holds notification data in MongoDB.
- **Order Service**: Handles order processing and stores data in PostgreSQL.
- **Payment Service**: Processes payments with data stored in PostgreSQL.
- **Config Service**: Provides configuration for other microservices.
- **Discovery Service**: Uses Eureka for service registration and discovery.
- **Gateway Service**: Routes incoming requests to the relevant microservice.

Additionally, RabbitMQ is used as a message broker to asynchronously communicate order and payment confirmations. When an order is accepted, the Order Service sends messages to RabbitMQ queues which the Notification Service consumes to trigger email notifications. Zipkin is implemented for distributed tracing across all services.

## Architecture

The architecture of this project includes the following components:

- **Microservices**: Each service is independently deployed and scalable.
- **Service Discovery**: Eureka is used to register and discover available services.
- **API Gateway**: Acts as the entry point for all client requests, forwarding them to the appropriate service.
- **Configuration Management**: A dedicated Config Service allows centralized management of microservice configurations.
- **Message Broker**: RabbitMQ handles asynchronous communication between services.
- **Distributed Tracing**: Zipkin helps in tracking requests across service boundaries.
- **Database Systems**:
  - **MongoDB**: Used for storing customer and notification data.
  - **PostgreSQL**: Used for storing product, order, and payment data.

## Technologies Used

- **Spring Boot**: For building robust and scalable microservices.
- **Eureka**: For service discovery and registration.
- **Spring Cloud Gateway**: To route incoming requests to the appropriate microservices.
- **RabbitMQ**: To facilitate asynchronous messaging between services.
- **Zipkin**: For distributed tracing and monitoring.
- **MongoDB & PostgreSQL**: As NoSQL and relational databases respectively, chosen for their suitability to different data types.
- **Docker & Docker Compose**: To containerize the application and manage multi-container deployments seamlessly.
- **MongoExpress**: For MongoDB web-based administration.
- **MailDev**: To simulate email services during development.

## Services Overview

- **Customer Service**: Handles all customer-related operations and stores customer information in MongoDB.
- **Product Service**: Manages product catalog and details, utilizing PostgreSQL for storage.
- **Notification Service**: Processes notifications, consuming messages from RabbitMQ to send email confirmations.
- **Order Service**: Manages order creation and processing; communicates order confirmations through RabbitMQ.
- **Payment Service**: Processes payment transactions and communicates with RabbitMQ for payment confirmation.
- **Config Service**: Provides externalized configuration for all microservices.
- **Discovery Service (Eureka)**: Keeps track of service instances and helps in dynamic discovery.
- **Gateway Service**: Acts as the central access point, routing client requests to the corresponding microservice.

## Installation and Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/your-repository.git
   cd your-repository

2. **Ensure Prerequisites Are Installed**
  - Docker
  - Docker Compose
  - Git

## Running the Project

The project includes a `docker-compose.yml` file that sets up the following containers:
- **PostgreSQL**: For product, order, and payment data.
- **MongoDB**: For customer and notification data.
- **MongoExpress**: To manage MongoDB.
- **RabbitMQ**: As the message broker.
- **Zipkin**: For distributed tracing.
- **MailDev**: For capturing emails during development.

To launch all services, run:
   ```bash
   docker-compose up
   ```

This command will pull the required Docker images (if not already available), create the containers, and start all services. You can then access the services as configured (e.g., API endpoints, RabbitMQ management console, etc.).


## Usage
- **API Gateway**: Access the gateway at the specified host/port in your `docker-compose.yml` to route requests.
- **Service Discovery**: Monitor the registered services via the Eureka dashboard.
- **Message Broker**: Check RabbitMQ’s management interface to view message queues.
- **Tracing**: Use the Zipkin UI to trace the journey of requests across your microservices.
- **Databases**:
  - MongoDB data can be viewed via MongoExpress.
  - PostgreSQL data can be accessed using your preferred SQL client.
