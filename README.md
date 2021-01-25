#### DEV

[![Build Status Dev](https://travis-ci.com/SadheeraMahanama/Wiley-Test-Backend.svg?token=hS9VGvZErZvUqky6CU8q&branch=dev)](https://travis-ci.com/SadheeraMahanama/Wiley-Test-Backend)
[![API Doc Dev](https://img.shields.io/badge/API-Doc-brightgreen)](https://documenter.getpostman.com/view/5662193/TW6tLq58)

#### PROD

[![Build Status Master](https://travis-ci.com/SadheeraMahanama/Wiley-Test-Backend.svg?token=hS9VGvZErZvUqky6CU8q&branch=master)](https://travis-ci.com/SadheeraMahanama/Wiley-Test-Backend)
[![API Doc Dev](https://img.shields.io/badge/API-Doc-brightgreen)](https://documenter.getpostman.com/view/5662193/TW6tLq58)
[![Deploy Prod](https://img.shields.io/badge/deploy-success-brightgreen)](https://d277f7c9q80vkp.cloudfront.net)


# Wiley Online Library - Coding Test

Simple Login/Registration page for Wiley Online Library.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Deploy](#deploy)
- [Choose of Technology](#technologies)

<a name="installation"></a>

## Installation

### Prerequisites

- Java 8+
- Maven 3.3+

### Configure Spring Datasource & SMTP Server

   - Configure in `src/main/resources/application.yml`

   ```
   spring:
      datasource:
         url: <db_connection_string>
         username: <db_username>
         password: <db_password>
      mail:
         host: <SMTP_host>
         port: <SMTP_port>
         username: <SMTP_username>
         password: <SMTP_password>
   ```

### Start App

- Clone this repo: `$ git clone https://github.com/SadheeraMahanama/Wiley-Test-Backend.git`
- Build this application: `$ mvn clean package spring-boot:repackage`
- Start this application: `$ mvn spring-boot:run`

<a name="usage"></a>

## Usage

Published API Doc: [https://documenter.getpostman.com/view/5662193/TW6tLq58](https://documenter.getpostman.com/view/5662193/TW6tLq58)

### Resources

**Example**

```
curl --location --request POST 'http://localhost:5000/auth/login' \
--data-raw '{
    "email": "<email>",
    "password": "<password>"
}'
```

**Response**

```json
{
    "success": true,
    "message": "Login Success",
    "accessToken": "eyJhb...",
    "tokenType": "Bearer",
    "user": {
        "id": 3,
        "name": "Sadheera Mahanama",
        "email": "sadheeramahanama94@gmail.com"
    }
}
```

<a name="deploy"></a>

## Deploy

#### Setup `aws-cli`

   - Refer: [Install AWS CLI](http://docs.aws.amazon.com/cli/latest/userguide/installing.html)

#### Setup `eb-cli`

   - Refer: [Install the EB CLI](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install.html)


#### Deploy
   - Deploy to EBS: 
      ```
      $ eb use WileyTestBackend-env
      $ eb deploy WileyTestBackend-env
      ```
   - Endpoint: `https://d277f7c9q80vkp.cloudfront.net`
   
   <a name="technologies"></a>

   ## Choose of Technology
   
   - **Spring Boot** - In short, Spring Boot is the combination of Spring Framework and Embedded Servers.There is no requirement for XML configuration (deployment descriptor). It uses convention over configuration software design paradigm that means it decreases the effort of the developer in Spring Boot. We should use Spring Boot Framework because the dependency injection approach is used in Spring Boot, it contains powerful database transaction management capabilities, it simplifies integration with other Java frameworks like JPA/Hibernate ORM, Struts, etc and it reduces the cost and development time of the application. 
   
   - **MySQL** - MySQL is currently the most popular database management system software used for managing the relational database. It is open-source database software, which is supported by Oracle Company. It is fast, scalable, and easy to use database management system in comparison with Microsoft SQL Server and Oracle Database. 
    
   
