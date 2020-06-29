This project was developed to demonstrate Springboot knowledgment about how to developer a simple REST API.

Some practices adopted was:

- I used reactive programming by implementing some classes as Mono and Flux.
- This project was made using JDK 1.8
- OpenAPI Specification was built to documenting the REST API.
- The application can be started using a Docker image, because within the project exist a maven plugin to build project docker image.
- The persistence layer used was MongoDB to demonstrate Java interacting with a NoSql database.

# Goal

This apllication consists in a REST API to manage cities and customer providing some endpoints to GET, POST, PATCH, DELETE the entities.

## Cities

There are 3 endpoints to manipulate cities in this project. These endpoints are:

| Http Verb | Endpoint | Description |
|---|---|---|
|`POST`|`/cities`|This resource was made to create a city|
|`GET`|`/cities`|This resource was made to list zero, one or more cities|
|`GET`|`/cities/{id}`|This resource was made to retrieve only a city|

## Customers

There are 5 endpoints to manipulate customers in this project. These endpoints are:

| Http Verb | Endpoint | Description |
|---|---|---|
|`POST`|`/customers`|This resource was made to create a customer|
|`GET`|`/customers`|This resource was made to list zero, one or more customers|
|`GET`|`/customers/{identifier}`|This resource was made to retrieve only a customer|
|`DELETE`|`/customers/{identifier}`|This resource was made to delete only a customer|
|`PATCH`|`/customers/{identifier}`|This resource was made to update only a customer fullname|

## Swagger

Swagger is a Open API specification that describes all the resources available in this project.

You can see the specification when the server will running at `http(s)://<server>:<port>/api/swagger-ui.html`.

# How to install

To run this project you need to have installed:

- JRE/JDK 1.8
- Maven
- Docker (only if you want)
- docker-compose (only if you want)
- Port 8080 not in use

## Using docker to run

I will explain the steps using docker to easy up the application. Feel free to run by your best opinion way.

1. `git clone https://github.com/ekiametis/springboot-application.git springboot-application` - Clone this repository
2. `cd springboot-application`- Enter in the project folder
3. `mvn clean install` - Run mvn to build a JAR File and Docker image
5. `docker-compose up -d` - Execute this command to up the application at `http://localhost:8080`

`NOTE`: Remember, to see the API specification, after you up the application, you can see the Open API Specification by accessing `http://localhost:8080/api/swagger-ui.html`
