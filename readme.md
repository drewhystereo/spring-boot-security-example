# RapiFuzz assignment project

#### An API-driven web application developed with Spring Boot and PostgreSQL for efficient incident reporting, management, and resolution. Features include user authentication with JWT, role-based access control, and comprehensive incident CRUD operations.
## Table of Contents

- [Running with Docker Compose](#running-with-docker-compose)
- [Database Setup](#database-setup)
- [API Usage](#api-usage)
    - [Sign Up](#sign-up)
    - [Sign In](#sign-in)
    - [Create Incident](#create-incident)
    - [Get Incidents for a Particular User](#get-incidents-for-a-particular-user)

## Running with Docker Compose

To start the PostgreSQL container, use the following command:

```sh
docker-compose up -d
```

This command will start the container in detached mode.
Ensure you have a docker-compose.yml file configured correctly in your project directory.

## Database Setup

After starting the PostgreSQL container, you need to insert initial data into the database. You can do this using pgAdmin or any other PostgreSQL client.
Run the following SQL command to insert roles into the roles table:

```sql
INSERT INTO roles(name) VALUES('ROLE_USER');
```

## APIs usage

### Sign Up
##### To create a new user account, use the following cURL command:

```java
curl --location 'localhost:9090/api/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"dhruv",
    "email":"dhruv@rapifuzz.com",
    "password":"dhruv#12345",
    "role":["user"]
}'
```

### sign in
##### To sign in and obtain a JWT token, use the following cURL command:



```java
curl --location 'localhost:9090/api/auth/signin' \
--header 'Content-Type: application/json' \
--data '{
    "username":"dhruv",
    "password":"dhruv#12345"
}'
```
### Create Incident
##### To create a new incident, use the following cURL command. Replace the Authorization header value with the JWT token obtained from the sign-in response:
```java
curl --location 'localhost:9090/api/incidents' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <your-jwt-token>' \
--data '{
    "incidentDetails":"once i got a job offer from rapifuzz...",
    "status":"OPEN",
    "priority":"HIGH"
}'
```

### Get Incidents for a Particular User
##### To get the incidents for a particular user using a JWT token, use the following cURL command. Replace the Authorization header value with the JWT token obtained from the sign-in response:
```java
curl --location 'localhost:9090/api/incidents' \
--header 'Authorization: Bearer <your-jwt-token>'
```


