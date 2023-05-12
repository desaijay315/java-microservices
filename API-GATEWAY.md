## Dependencies

The following are the dependencies defined in the `pom.xml` file for the Order Service API:

### Spring Boot Starter Dependencies

- `spring-boot-starter-actuator`: Provides production-ready features to help you monitor and manage your application.
- `spring-boot-starter-data-jpa`: Provides Spring Data JPA and Hibernate support.
- `spring-boot-starter-web`: Provides Spring MVC and embedded Tomcat server.

### Tracing and Logging Dependencies

- `micrometer-tracing-bridge-brave`: Provides a Micrometer tracing bridge for the Brave tracing system.
- `zipkin-reporter-brave`: Provides a Zipkin reporter for Brave tracing system.

### Spring Cloud Dependencies

- `spring-cloud-starter-netflix-eureka-client`: Provides integration with Netflix Eureka service discovery.

### Database and Testing Dependencies

- `mysql-connector-j`: Provides the MySQL JDBC driver to communicate with MySQL databases.
- `spring-boot-starter-test`: Provides testing support for Spring Boot applications.

### Other Dependencies

- `lombok`: Provides annotation-based shortcuts for common Java code patterns.
- `spring-cloud-starter-config`: Provides Spring Cloud Config support to externalize configuration properties.
- `spring-boot-devtools`: Provides additional development-time features to improve developer productivity.
- `spring-cloud-starter-openfeign`: Provides integration with the OpenFeign client to simplify RESTful service calls.
- `spring-cloud-starter-circuitbreaker-resilience4j`: Provides integration with Resilience4j, a lightweight fault tolerance library.


### Lombok is a Java library that helps to reduce boilerplate code by generating getters, setters, constructors, and other utility methods using annotations. Here is a list of some common Lombok annotations along with their brief descriptions:

1. `@Getter`: Generates a getter method for the annotated field.
2. `@Setter`: Generates a setter method for the annotated field.
3. `@ToString`: Generates a `toString()` method for the class based on the fields.
4. `@EqualsAndHashCode`: Generates `equals()` and `hashCode()` methods for the class based on the fields.
5. `@NoArgsConstructor`: Generates a no-argument constructor for the class.
6. `@AllArgsConstructor`: Generates an all-arguments constructor for the class, where each argument corresponds to a field in the class.
7. `@RequiredArgsConstructor`: Generates a constructor for the class with required arguments only. Fields marked with `final` or annotated with `@NonNull` are considered required.
8. `@Data`: A shortcut annotation that combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, and `@RequiredArgsConstructor`.
9. `@Builder`: Generates a builder pattern implementation for the class, allowing you to create instances using a fluent API.
10. `@SneakyThrows`: Allows you to throw checked exceptions without explicitly declaring them in the method's `throws` clause.
11. `@Value`: A shortcut annotation that combines `@Getter`, `@ToString`, `@EqualsAndHashCode`, `@AllArgsConstructor`, and makes all fields `final`. Useful for creating immutable value classes.
12. `@NoArgsConstructor(force = true)`: Generates a no-argument constructor for the class, setting all fields to their default values (even for `final` fields).
13. `@NonNull`: An annotation used on a field or method parameter to indicate that null values are not allowed. When used with `@RequiredArgsConstructor`, a null-check is added to the constructor.
14. `@Cleanup`: Ensures that the resource (like `InputStream`, `OutputStream`, `Reader`, or `Writer`) is closed after it is used, even if an exception is thrown.
15. `@Delegate`: Automatically delegates method calls from the annotated field to the field's type.
16. `@With`: Generates "with" methods for the annotated field. A "with" method is like a setter, but it returns a new instance of the class with the new field value, rather than modifying the current instance.

You can find more information and examples of these annotations in the Lombok documentation: [https://projectlombok.org/features/all](https://projectlombok.org/features/all)


### Creating a separate ProductRequest model is useful for several reasons:

1. Separation of concerns: It allows you to separate the incoming request data representation from the actual entity representation in your application. This way, you can make changes to the request model without affecting the underlying entity directly.

2. Validation: By creating a separate request model, you can apply validation rules specific to incoming requests. For example, you may want to ensure that certain fields are provided in the request or meet specific criteria. Using a separate request model makes it easier to apply and maintain these validation rules.

3. Data mapping: Sometimes, the incoming request data may not match the structure of the entity you're working with. In these cases, using a separate request model makes it easier to map the incoming data to the entity properties.

4. Flexibility: Having a separate request model allows you to evolve your API without impacting the underlying entity. For example, you might want to add additional fields to your request model that are not part of the entity, such as metadata or user-specific information.


### ProductController API Endpoints

The `ProductController` class includes the following API endpoints, which cover all the business logic implemented in the `ProductServiceImpl` class:

- **Create a new product (HTTP POST)**\
  Endpoint: `/product`\
  Method: `createProduct`

- **Get a single product by its ID (HTTP GET)**\
  Endpoint: `/product/{id}`\
  Method: `getProductById`

- **Get all products (HTTP GET)**\
  Endpoint: `/product`\
  Method: `getAllProducts`

- **Update a product by its ID (HTTP PUT)**\
  Endpoint: `/product/{id}`\
  Method: `updateProduct`

- **Delete a product by its ID (HTTP DELETE)**\
  Endpoint: `/product/{id}`\
  Method: `deleteProduct`

- **Get products within a specified price range (HTTP GET)**\
  Endpoint: `/product/price-range?min={min}&max={max}`\
  Method: `getProductsByPriceRange`
  
  
## API Endpoints

### Create a Product

`POST /product/create-product`

Creates a new product with the given details.

#### Request Body

```json
{
  "productName": "Sample Product",
  "price": 10,
  "quantity": 100
}
```

#### Response

```json
{
  "productId": 1,
  "productName": "Sample Product",
  "price": 10,
  "quantity": 100
}
```

### Get All Products

`GET /product`

Returns a list of all available products.

#### Response

```json
[
  {
    "productId": 1,
    "productName": "Product 1",
    "price": 10,
    "quantity": 100
  },
  {
    "productId": 2,
    "productName": "Product 2",
    "price": 20,
    "quantity": 50
  },
  {
    "productId": 3,
    "productName": "Product 3",
    "price": 30,
    "quantity": 25
  },
  ...
]
```

### Get a Product by ID

`GET /product/{id}`

Returns the product with the given ID.

#### Response

```json
{
  "productId": 1,
  "productName": "Product 1",
  "price": 10,
  "quantity": 100
}
```

### Update a Product

`PUT /product/{id}`

Updates the product with the given ID.

#### Request Body

```json
{
  "productName": "Updated Product",
  "price": 15,
  "quantity": 75
}
```

#### Response

```json
{
  "productId": 1,
  "productName": "Updated Product",
  "price": 15,
  "quantity": 75
}
```

### Delete a Product

`DELETE /product/{id}`

Deletes the product with the given ID.

#### Response

```json
{
  "productId": 1,
  "productName": "Product 1",
  "price": 10,
  "quantity": 100
}
```


### POST /orders/placeOrder

**Request Body:**

The request body should be a JSON object that includes the `productId` and `quantity` fields. For example:

```json
{
  "productId": 123,
  "quantity": 10
}
```

**Response Body:**

If the order is placed successfully, the response body will be a JSON object representing the created order, which includes the `id`, `productId`, `quantity`, `orderDate`, `orderStatus`, and `amount` fields. For example:

```json
{
  "id": 1,
  "productId": 123,
  "quantity": 10,
  "orderDate": "2022-05-01T10:10:10Z",
  "orderStatus": "PROCESSING",
  "amount": 100
}
```

### GET /orders/{orderId}

**Response Body:**

If the order is found, the response body will be a JSON object representing the order, which includes the `id`, `productId`, `quantity`, `orderDate`, `orderStatus`, and `amount` fields. For example:

```json
{
  "id": 1,
  "productId": 123,
  "quantity": 10,
  "orderDate": "2022-05-01T10:10:10Z",
  "orderStatus": "PROCESSING",
  "amount": 100
}
```

If the order is not found, the response body will be empty.

### GET /orders

**Response Body:**

If there are orders available, the response body will be a JSON array of objects, each representing an order. Each object includes the `id`, `productId`, `quantity`, `orderDate`, `orderStatus`, and `amount` fields. For example:

```json
[
  {
    "id": 1,
    "productId": 123,
    "quantity": 10,
    "orderDate": "2022-05-01T10:10:10Z",
    "orderStatus": "PROCESSING",
    "amount": 100
  },
  {
    "id": 2,
    "productId": 456,
    "quantity": 5,
    "orderDate": "2022-05-02T10:10:10Z",
    "orderStatus": "COMPLETED",
    "amount": 50
  }
]
```

If there are no orders available, the response body will be an empty array.



# API-Gateway

This project is a Spring Cloud Gateway that routes and limits requests to three different services: `order-service`, `payment-service`, and `product-service`. If any of these services start failing, the gateway will redirect requests to fallback URIs.

## Configuration

```yaml
server:
  port: 9090

spring:
  application:
    name: API-GATEWAY
  config:
    import: configserver:http://localhost:8088
  cloud:
    gateway:
      routes:
        - id : order-service
          uri: lb://order-service
          predicates:
            - Path=/order/**
          filters:
            - name: CircuitBreaker
              args:
                name: order-service
                fallbackuri: forward:/orderServiceFallBack
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 1
                redis-rate-limiter.burstCapacity: 1
        - id: payment-service
          uri: lb://payment-service
          predicates:
            - Path=/payment/**
          filters:
            - name: CircuitBreaker
              args:
                name: payment-service
                fallbackuri: forward:/paymentServiceFallBack
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 1
                redis-rate-limiter.burstCapacity: 1
        - id: product-service
          uri: lb://product-service
          predicates:
            - Path=/product/**
          filters:
            - name: CircuitBreaker
              args:
                name: product-service
                fallbackuri: forward:/productServiceFallBack
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 1
                redis-rate-limiter.burstCapacity: 1



## Explanation

- `server.port: 9090`: The server port listening for incoming requests at port 9090.

- `spring.application.name: API-GATEWAY`: The application's name, useful for identification in a distributed system.

- `spring.config.import: configserver:http://localhost:8088`: Used by Spring Cloud Config to import configuration properties from a centralized configuration server running at `http://localhost:8088`.

- `spring.cloud.gateway.routes`: The route definitions for the API Gateway. Each route definition contains:

    - `id`: A unique identifier for the route.

    - `uri`: The address of the downstream service to which requests will be forwarded. In this case, `lb://order-service` means that requests will be load balanced (`lb`) between instances of the `order-service`, `payment-service`, or `product-service`.

    - `predicates`: Conditions for when the route should be taken. For example, `Path=/order/**` means any URL path that starts with `/order` will be routed to the `order-service`.

    - `filters`: Modify the incoming or outgoing requests or responses. In this case, two filters are defined:
        - `CircuitBreaker`: Integrates with Spring Cloud Circuit Breaker. If the `order-service` starts failing, the circuit breaker will 'open' and forward requests to `/orderServiceFallBack`, `/paymentServiceFallBack`, or `/productServiceFallBack` (depending on the service) instead of trying to reach the failing service.
        - `RequestRateLimiter`: Uses Redis to limit the rate of incoming requests. `redis-rate-limiter.replenishRate` is the number of requests per second that a user can make, and `redis-rate-limiter.burstCapacity` is the maximum number of requests a user can make in a single second if they haven't made any requests for a while.
