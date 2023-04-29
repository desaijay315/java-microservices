###Lombok is a Java library that helps to reduce boilerplate code by generating getters, setters, constructors, and other utility methods using annotations. Here is a list of some common Lombok annotations along with their brief descriptions:

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


###Creating a separate ProductRequest model is useful for several reasons:

1. Separation of concerns: It allows you to separate the incoming request data representation from the actual entity representation in your application. This way, you can make changes to the request model without affecting the underlying entity directly.

2. Validation: By creating a separate request model, you can apply validation rules specific to incoming requests. For example, you may want to ensure that certain fields are provided in the request or meet specific criteria. Using a separate request model makes it easier to apply and maintain these validation rules.

3. Data mapping: Sometimes, the incoming request data may not match the structure of the entity you're working with. In these cases, using a separate request model makes it easier to map the incoming data to the entity properties.

4. Flexibility: Having a separate request model allows you to evolve your API without impacting the underlying entity. For example, you might want to add additional fields to your request model that are not part of the entity, such as metadata or user-specific information.


###ProductController API Endpoints

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