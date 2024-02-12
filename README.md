# Chatop
This back-end application is an API which manages many routes. (GET/POST/PUT)

This one provides informations such as users, rentals, messages.

## Getting Started

### Prerequisites
* Maven
* Java 21 
* Angular 15

## Install Database 

1. Download MySQL Installer and follow steps for installation :
https://dev.mysql.com/downloads/installer/
2. After installation, run 'MySQL Command Line Client' Application 
3. Create Database for API
  ```sh
  CREATE DATABASE chatop;
  ```
4. Run script SQL 
  ```sh
  USE chatop;
  SOURCE C:/{FOLDER_OF_APPLICATION_ANGULAR}/ressources/script.sql
  ```

## Install the project

1. Angular application
* Install NPM packages
  ```sh
  npm install
  ```
 
* Builds and serves application
  ```sh
  ng serve
  ```

2. API Application

* Install all dependencies and create jar executable :
  ```sh
  Run ./mvnw clean install
  ```

* Run API project :
  ```sh
  java -jar api-0.0.1-SNAPSHOT.jar
  ```

## Usage

1. Navigate to : http://localhost:4200/. The main page shows login/register by default.

2. After you login/register , you could create/see rentals informations.

## Language/Framework
* Mysql/Java/Lombok/Spring boot/Spring Security(JWT authentication)

* Mockoon/Postman

<!-- CONTACT -->
## Contact

Paysant Gérald - geraldpaysant@gmail.com

Project Link (API): [https://github.com/gpaysant/Developpez-le-back-end-en-utilisant-Java-et-Spring_api](https://github.com/gpaysant/Developpez-le-back-end-en-utilisant-Java-et-Spring_api)

Project Link (Angular): [https://github.com/gpaysant/Developpez-le-back-end-en-utilisant-Java-et-Spring](https://github.com/gpaysant/Developpez-le-back-end-en-utilisant-Java-et-Spring)
