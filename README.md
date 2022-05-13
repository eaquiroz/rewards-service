# rewards-app
	
	A retailer offers a rewards program to its customers awarding points based on each recorded purchase

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a application on your local machine. One way is to execute the `main` method in the `com.rewards.service.RewardsServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Executing the Junit test cases

Executing the below command to run all the junit test cases

```shell
mvn clean test
```

## Consuming the API

	Application will be accessible on localhost:8085. End-point /rewards/{userId} exposed.

	API GET Method : http://localhost:8085/rewards/john

	Success response :- {
		"name": "John Smith",
		"rewards": 50
	}

	Error response :- {
		"timestamp": "2022-05-12T10:46:35.237+00:00",
		"status": 404,
		"error": "Not Found",
		"message": "Customer not found",
		"path": "/rewards/123"
	}

Customer Data Store in H2 database and it will be populated using the SQL script file.

	|User Id | Amount Purchase|
	|--------|----------------|
	|john	 | 100$           |
	|kelvin	 | 120$           |
	|peter 	 | 70$            |

