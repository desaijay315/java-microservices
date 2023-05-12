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



# Spring Security

Okta OpenID Connect Integration with Spring Boot

## Description

This project demonstrates the integration of Okta's OpenID Connect for authentication in a Spring Boot application. It utilizes Spring Security to secure the application and enables users to authenticate using OAuth 2.0 and JWT (JSON Web Tokens) with Okta as the identity provider.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Okta
- JWT


## Configuration

The main configuration code in the project is found in the `OktaOAuth2WebSecurity` class. Here's an explanation of the configuration:

- `@Bean` annotation: Indicates that the method should be treated as a bean and its return value should be registered in the Spring application context.

- `SecurityWebFilterChain`: Represents the security filter chain for the application. It defines the security rules and filters to be applied to incoming HTTP requests.

- `authorizeExchange()`: Configures the authorization rules for different exchange patterns. In this case, `anyExchange().authenticated()` is used to require authentication for any incoming request.

- `oauth2Login()`: Configures the OAuth 2.0 login flow, enabling the login process using OAuth 2.0. It sets up the necessary authentication filters and handlers.

- `oauth2ResourceServer()`: Configures the application as an OAuth 2.0 resource server, allowing it to validate and process OAuth 2.0 access tokens for authentication and authorization.

- `jwt()`: Configures the resource server to validate and process JWT (JSON Web Token) access tokens.

- `return http.build()`: Returns the built ServerHttpSecurity object representing the configured security filter chain.

### Authentication controller

The main configuration for the authentication controller is in the AuthenticationController class. It handles the /authenticate/login endpoint and retrieves the user's authentication details from the OidcUser and OAuth2AuthorizedClient objects.

Please make sure to configure the Okta integration properties in the application's properties file before running the application.

## Usage

To use the application:

1. Launch the application.
2. Access the application in your browser.
3. You will be redirected to the Okta login page to authenticate.
4. After successful authentication, you will be redirected back to the application.
5. You can now access the protected resources and endpoints in the application.


The provided code is a Spring Boot REST controller that handles the authentication-related endpoints. Let's go through the code and explain its functionality:

```java
package com.example.apigateway.controller;

import com.example.apigateway.model.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
```

- The `AuthenticationController` class is a Spring Boot REST controller annotated with `@RestController`. It handles authentication-related endpoints.

```java
@GetMapping("/login")
public ResponseEntity<AuthenticationResponse> login(
        @AuthenticationPrincipal OidcUser oidcUser,
        Model model,
        @RegisteredOAuth2AuthorizedClient("okta")
        OAuth2AuthorizedClient client
) {
```

- The `login` method is annotated with `@GetMapping("/login")` and handles GET requests to the "/authenticate/login" endpoint.
- The method parameters include:
  - `@AuthenticationPrincipal OidcUser oidcUser`: Annotating this parameter with `@AuthenticationPrincipal` allows the injection of the currently authenticated user object, `OidcUser`. It provides access to the user's details, such as the email.
  - `Model model`: This parameter allows adding attributes to the model for view rendering. However, it is not used in this code snippet.
  - `@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client`: Annotating this parameter with `@RegisteredOAuth2AuthorizedClient("okta")` allows the injection of the authorized OAuth2 client details for the "okta" client registration. It represents the client's access and refresh tokens.

```java
AuthenticationResponse authenticationResponse
        = AuthenticationResponse.builder()
        .userId(oidcUser.getEmail())
        .accessToken(client.getAccessToken().getTokenValue())
        .refreshToken(client.getRefreshToken().getTokenValue())
        .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
        .authorityList(oidcUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .build();
```

- An `AuthenticationResponse` object is created using a builder pattern.
- The `userId` field is set to the user's email obtained from the `oidcUser` object.
- The `accessToken` field is set to the value of the access token obtained from the `client` object.
- The `refreshToken` field is set to the value of the refresh token obtained from the `client` object.
- The `expiresAt` field is set to the epoch second value of the access token's expiration time obtained from the `client` object.
- The `authorityList` field is set to a list of the user's authorities (roles) obtained from the `oidcUser` object.

```java
return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
}
```

- The `AuthenticationResponse` object is returned as the response body using `ResponseEntity`, with an HTTP status of OK (200).

To use this controller:

1. Make a GET request to `/authenticate/login`.
2. The controller will retrieve the authenticated user's information, including the email, access token, refresh token, expiration time, and authorities.
3. The response will contain

## Contributing

Contributions to this project are welcome. If you find any issues or want to suggest improvements, feel free to submit a pull request.

## Author

Jay Desai