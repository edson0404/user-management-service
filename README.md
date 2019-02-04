# user-management-service
C.R.U.D Rest service(Java | Spring-boot | JPA | Hibernate | Docker)

## Getting Started

Git clone https://github.com/edson0404/user-management-service.git

### Prerequisites

Software to install.

```
Java 1.8 >
Docker
IntelliJ IDEA

```

### Ports
```
http://localhost:8080 - Spring boot Application

http://localhost:3306 - MySQl docker container
```

## Running the Application

To Run the user-management-service either use the gradle command or docker-compose.

#### Gradle Command 
```
 uncomment H2 DB configs in application.properties
```

```
./gradlew clean bootRun
```

#### Docker
```
uncomment  MySql docker DB configs in application.properties
```
```
docker-compose -f docker-compose.yaml up --renew-anon-volumes user-management-service 
```
#### MySql Docker DB
```
Login to mssql docker exec -it crud-mysql mysql -p 
Enter password: password
Query: 
describe test.users;
describe test.tasks;
```
## Curl commands to test (Note: \\")
#### User
```
curl -i -X POST -H "Content-Type:application/json" -d "{\"username\" : \"jsmith\", \"first_name\": \"John\", \"last_name\": \"Smith\"}" http://localhost:8080/api/user
curl -i -H "Content-Type: application/json" -X PUT -d "{\"first_name\" : \"John\", \"last_name\" : \"Doe\"}" http://localhost:8080/api/user/{userId}
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{userId}
```
#### Task
```
curl -i -H "Content-Type: application/json" -X POST -d "{\"name\": \"My task\", \"description\" : \"Description of task\", \"date_time\" : \"2016-05-25 14:25:00\"}" http://localhost:8080/api/user/{userId}/task
curl -i -H "Content-Type: application/json" -X PUT -d '{"name":"My updated task"}' http://localhost:8080/api/user/{userId}/task/{taskId}
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{userId}/task/{taskId}
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{userId}/task
curl -i -H "Content-Type: application/json" -X DELETE http://localhost:8080/api/user/{userId}/task/{taskId}
```
## Running the tests

To Execute the unit test run the following gradle command (Note:Use the gradle-wrapper)
```
./gradlew test
```

## Authors

* **Edson van Wyk** - *Git Repo* - [edson0404](https://github.com/edson0404)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

