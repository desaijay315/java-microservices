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