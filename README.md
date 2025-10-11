# csc340-crud-api
## Notes
### Java - [Spring ORM with JPA and Hibernate](https://medium.com/@burakkocakeu/jpa-hibernate-and-spring-data-jpa-efa71feb82ac)
- We are using ORM (Object-Relational Mapping) to deal with databases. This is a technique that allows us to interact with a relational database using object-oriented programming principles.
- JPA (Jakarta Persistence, formerly Java Persistence API) is a specification that defines ORM standards in Java. It provides an abstraction layer for ORM frameworks to make concrete implementations.
- Hibernate: Hibernate is a popular ORM framework that implements JPA. It simplifies database operations by mapping Java objects to database tables and handling queries efficiently.
Spring ORM allows seamless integration of Hibernate and JPA, making database interactions more manageable and reducing boilerplate code.
### DogsX Java classes have different purposes: Separation of concerns!
- [Entity]
  - The Dog class is annotated as an `@Entity `. This is used to map class attributes to database tables and SQL types.
  - We also annotated with `@Table` to give Hibernate directions to use this specific table name. This is optional but it helps with naming conventions.
  - Any Entity must have at least one attribute that is annotated as an `@Id`. In our case it's conveniently the `dogId` attribute.
    - We are also using an autogeneration strategy for the ID. This way we are not manually assigning IDs to our Dogs. This is optional.
       - For this reason, we also added a constructor to make a Dog without an ID.
  - An Entity must have a no-argument constructor.
- [Repository]
  - We are using an extension of the JPA Repository that comes with prebuilt database operations such as select all, select by id, select by any other reference, insert, delete, etc.
  - Annotate it as a `@Repository`.
  - We parametrize this using our object and its ID type.
    - `public interface DogRepository extends JpaRepository<Dog, Long>` => We want to apply the JPA repository operations on the `Dog` type. The `Dog` has an ID of type `long`.
  - If we need special database queries that are not the standard ones mentioned above, we can create [a method with a special purpose query] as shown. This is an interface so no implementation body.
- [Service]
  - Annotated as a `@Service`.
  - It is the go-between from controller to database. In here we define what functions we need from the repository. A lot of the functions are default functions that our repository inherits from JPA (save, delete, findAll, findByX), some of them are custom made (getDogBreed, getDogByName).
  - It asks the repository to perform SQL queries.
  - The Repository class is [`@Autowired`]. This is for managing the dependency to the repository. Do not use a constructor to make a Repository object, you will get errors.
- [Rest Controller]
  - Annotated as a `@RestController`.
  - It asks the Service class to perform data access functions.
  - The Service class is [`@Autowired`]
## API Endpoints
Base URL: [`http://localhost:8080/dogs`](http://localhost:8080/dogs)


1. ### [`/`](http://localhost:8080/dogs) (GET)
Gets a list of all Dogs in the database.

#### Response - A JSON array of Dog objects.

 ```
[
  {
    "dogId": 1,
    "name": "Max",
    "description": "Friendly golden retriever",
    "breed": "Golden Retriever",
    "age": 3.0,
    "activeDate": "2025-10-10"
  },
  {
    "dogId": 2,
    "name": "Chole",
    "description": "Golden Brown, Dark eyes, long tail, curly hair",
    "breed": "Golden Doodle",
    "age": 8.0,
    "activeDate": "2025-10-10T00:00:00.000+00:00"
  }
]
```

2. ### [`/{dogsId}`](http://localhost:8080/dogs/1) (GET)
Gets an individual dog in the system. Each dog is identified by a numeric `dogsId`

#### Parameters
- Path Variable: `dogsId` &lt;Long &gt; - REQUIRED

#### Response - A single Dog

```
  {
     "dogId": 1,
    "name": "Max",
    "description": "Friendly golden retriever",
    "breed": "Golden Retriever",
    "age": 3.0,
    "activeDate": "2025-10-10"
  }
```

3. ### [`/name`](http://localhost:8080/dogs/name?search=Chloe) (GET)
Gets a list of dogs with a name that contains the given string.

#### Parameters
- query parameter: `search` &lt; String &gt; - REQUIRED

#### Response - A JSON array of Dog objects.

```
[
  {
    "dogId": 2,
    "name": "Chole",
    "description": "Golden Brown, Dark eyes, long tail, curly hair",
    "breed": "Golden Doodle",
    "age": 8.0,
    "activeDate": "2025-10-10T00:00:00.000+00:00"
  }
]
```

4. ### [`/breed/{breed}`](http://localhost:8080/dogs/breed/Golden Doodle) (GET)
Gets a list of dogs for a breed.

#### Parameters
- path variable: `breed` &lt; String &gt; - REQUIRED

#### Response - A JSON array of Dog objects.

```
[
  {
     "dogId": 2,
    "name": "Chole",
    "description": "Golden Brown, Dark eyes, long tail, curly hair",
    "breed": "Golden Doodle",
    "age": 8.0,
    "activeDate": "2025-10-10"
  }
]
```

6. ### [`/`](http://localhost:8080/dogs) (POST)
Create  a new Dog entry

#### Request Body
A Dog object. Note the object does not include an ID as this is autogenerated.
```
{
    "name": "Buster",
    "description": "short hair, black with brown face , short tail",
    "breed": "Dobermann",
    "age": 5.0,
    "activeDate": "2025-10-10"
}
```
#### Response - The newly created Dog.

```
  {
    "dogId": 3,
    "name": "Buster",
    "description": "short hair, black with brown face , short tail",
    "breed": "Dobermann",
    "age": 5.0,
    "activeDate": "2025-10-10"
  }
```

7. ### [`/{dogId}`](http://localhost:8080/dogs/3) (PUT)
Update an existing dogs.

#### Parameters
- Path Variable: `dogId` &lt;integer&gt; - REQUIRED

#### Request Body
A dog object with the updates.
```
{
    "Id": 3,
    "name": "Buster",
    "description": "short hair, black with brown face , short tail",
    "breed": "Dobermann",
    "age": 5.0,
    "activeDate": "2025-10-10"
}
```
#### Response - the updated dog object.
```
{
    "dogsId": 3,
    "name": "Buster",
    "description": "short hair, black with brown face , short tail",
    "breed": "Dobermann",
    "age": 5.0,
    "activeDate": "2025-10-10"
}
```

8. ### [`/{dogId}`](http://localhost:8080/dogs/3) (DELETE)
Delete an existing Dog.

#### Parameters
- Path Variable: `dogId` &lt;integer&gt; - REQUIRED

#### Response - the updated list of Dogs.
```
[
  {
    "dogId": 1,
    "name": "Max",
    "description": "Friendly golden retriever",
    "breed": "Golden Retriever",
    "age": 3.0,
    "activeDate": "2025-10-10"
  },
  {
    "dogId": 2,
    "name": "Chole",
    "description": "Golden Brown, Dark eyes, long tail, curly hair",
    "breed": "Golden Doodle",
    "age": 8.0,
    "activeDate": "2025-10-10"
  }
]
```