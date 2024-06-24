# Kafka Microservices Project

## Overview
This project demonstrates a simple microservice architecture using Apache Kafka for real-time data streaming. The setup includes two microservices:
1. **Kafka Producer**: Reads real-time stream data from Wikimedia API and writes it into Kafka topics.
2. **Kafka Consumer**: Consumes the real-time stream data from Kafka topics and writes it into a MySQL database.

![Kafka-Microservices Architecture](https://github.com/ism-at/Kafka-Microservices/blob/main/Kafka-Mocroservices.jpeg)

## Architecture
1. **Wikimedia API**: Source of real-time stream data.
2. **Microservice 1: Kafka Producer**: Reads data from Wikimedia API and produces messages to Kafka topics.
3. **Kafka Broker**: Manages topics and stores log of messages.
   - **Topic 1/Log**
   - **Topic 2/Log**
4. **Microservice 2: Kafka Consumer**: Consumes messages from Kafka topics and writes data into a MySQL database.
5. **MySQL Database**: Stores the consumed data.

## Prerequisites
- Java 19
- Apache Kafka
- Zookeeper
- Maven
- MySQL

## Installation

### Step 1: Install and Configure Apache Kafka and Zookeeper

1. **Download Kafka**:
   ```sh
   wget https://downloads.apache.org/kafka/3.2.1/kafka_2.13-3.2.1.tgz
   tar -xzf kafka_2.13-3.2.1.tgz
   cd kafka_2.13-3.2.1

Start Zookeeper:

    bin/zookeeper-server-start.sh config/zookeeper.properties

Start Kafka Broker:
Open a new terminal and navigate to the Kafka directory.

    cd kafka_2.13-3.2.1
    bin/kafka-server-start.sh config/server.properties

### Step 2: Setup MySQL Database

    Install MySQL:
    Follow the instructions for your operating system to install MySQL.

    Create Database and User:

    sql

    CREATE DATABASE kafka_microservices;
    CREATE USER 'kafka_user'@'localhost' IDENTIFIED BY 'password';
    GRANT ALL PRIVILEGES ON kafka_microservices.* TO 'kafka_user'@'localhost';
    FLUSH PRIVILEGES;

### Step 3: Setup Microservices

Clone the Repository:

    git clone https://github.com/ism-at/Kafka-Microservices.git
    cd kafka-microservices

Navigate to Producer Directory and Run the Producer:

    cd producer
    mvn spring-boot:run

Navigate to Consumer Directory and Run the Consumer:

    cd consumer
    mvn spring-boot:run

Configuration

Ensure your application.properties or application.yml files are correctly configured for both producer and consumer services.
Producer application.properties:

properties

    spring.kafka.bootstrap-servers=localhost:9092
    wikimedia.api.url=https://stream.wikimedia.org/v2/stream/recentchange

Consumer application.properties:

properties

    spring.kafka.bootstrap-servers=localhost:9092
    spring.kafka.consumer.group-id=wikimedia-group
    spring.kafka.consumer.auto-offset-reset=earliest
    spring.datasource.url=jdbc:mysql://localhost:3306/kafka_microservices
    spring.datasource.username=kafka_user
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update

Usage

    Producer: Fetches real-time data from the Wikimedia API and publishes it to Kafka topics.
    Consumer: Consumes data from Kafka topics and stores it in the MySQL database.
