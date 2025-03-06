# Aikido Training Session Tracker

### Project Overview

The Aikido Training Session Tracker is a backend system designed to manage students, instructors, training sessions, attendance, and performance progress. The project utilizes Hibernate features such as entity events, converters, and object concurrency to ensure efficient data management.

#### Setup Instructions

**Prerequisites**

**Ensure you have the following installed:**

- Java 21 or later

- Maven

- MariaDB

- Git

#### Clone repository

```
git clone https://github.com/teemueka/wk7_InClass.git
cd wk7_InClass
```

### Configure Database

Update the *persistence.xml* file located in src/main/resources/META-INF/ with your database credentials

### Build and Run

```
mvn clean install
java -jar target/wk7_InClass.jar
```
