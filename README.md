# Skylink

---
## Introduction

---
A flight reservation system to display all available flights
for passengers to make reservations. 
Once a flight reservation is completed, 
then proceed to make a payment transaction to purchase 
the reservation.
## Getting Started

---
## Build & Run

---
The project requires Java 17 to develop and run and uses 
Spring Boot 3.X. The latest version can be downloaded from
https://adoptium.net/

Once it is installed, check the version from the shell:
```
java --version
```
The output must show a text similar to the one bellow 
(maybe a newer version is acceptable):
```
openjdk 17.0.9 2023-10-17
OpenJDK Runtime Environment Temurin-17.0.9+9 (build 17.0.9+9)
OpenJDK 64-Bit Server VM Temurin-17.0.9+9 (build 17.0.9+9, mixed mode, sharing)
```
### IDE
Load the project in any IDE which support Apache Maven 
(Eclipse, IntelliJ, VS Code). 
There is a main class called `SkylinkApplication`, 
just run it, and it will start the Spring Boot. 
Access the application at http://localhost:8080
### Shell
Apache Maven is used to build the project.

`mvn clean install -DskipTests` can be used to compile 
the application.

`mvn spring-boot:run` can be used to run the application 
using Apache Maven
### Tests
`mvn clean test` can be used to compile and run tests only.
### Database

---
This project uses MySQL for the data store. 
The database needs to be provided as an external dependency.
Run the following statements (under root user) to create 
an empty database and a user for application access 
(change the database & user if desired):

```
CREATE USER 'skylink'@'%' IDENTIFIED BY 'skylink123'; 
CREATE DATABASE skylink CHARACTER SET utf8 COLLATE utf8_bin; 
GRANT ALL ON skylink.* TO 'skylink'@'%';
GRANT SELECT ON mysql.* TO skylink;
FLUSH PRIVILEGES; 
```
### Search Engine

---
This project uses Apache Lucene as a search engine.

### Configuration

---
Under application.properties the database name or the database user/password can be changed.







