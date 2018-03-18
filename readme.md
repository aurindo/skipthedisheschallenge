#Skip the Dishes Challenge

##Stack
 - Java 8
 - Postgres 9
 - Springboot
 - Spring Cloud
 - RabbitMQ (www.cloudamqp.com <= RabbitMQ on the Cloud)
 - Swagger   **[NOT finished]**

### Configuration Server
- It is a server to centralize the configutarion of all microservices.
	```
	$ cd config
	$ mvn clean isntall spring-boot:run
	```
### Discovery Server
- It is a server to register automatically the address of microservices, with this is more simple for one microservice localize another.
```
	$ cd discovery
	$ mvn clean isntall spring-boot:run
```
### Gateway Server
- It is a server to make a gateway to unify all API calls in a unique address.
```
	$ cd discovery
	$ mvn clean isntall spring-boot:run
```
### Challenge Service
 - This service contain an API to make possible create a new food order.

1. Add a new customer
```
curl -H "Content-Type: application/json" -X POST -d '{
	"name":"John"
    "login": "admin",
    "password": "password"
}' http://localhost:8085/users/sign-up
```

2. Login
```
curl -H "Content-Type: application/json" -X POST -d '{
    "login": "admin",
    "password": "password"
}' http://localhost:8085/login
```
*Save the token!!*

3. List products
```
curl -H "Content-Type: application/json" -X GET http://localhost:8085/products
```





