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
