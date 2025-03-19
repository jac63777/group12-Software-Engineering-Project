# Cinema E-Booking System (Spring Boot + React)

This project is a **full-stack movie booking system** with a **Spring Boot backend** and a **React frontend**.

---

## How to Run the Project
You can run the project in **two ways**:
1. **Using the provided scripts** (`start_project.sh` and `stop_project.sh`)
2. **Manually starting frontend & backend separately**

---

## Running the Project with Scripts
To **start** the project (backend + frontend in separate terminals):
```sh
./start_project.sh

To **stop** the Project
```sh
./stop_project.sh

---


`start_project.sh` will:
- Kill any existing backend (port `8080`) and frontend (port `5173`).
- Start the backend in a new terminal.
- Start the frontend in a new terminal.
- Open the browser at `http://localhost:5173/`.

`stop_project.sh` will:
- Find and terminate any backend and frontend processes.

---

## Running the Backend Manually
If you want to start the backend without using the script:

1. Navigate to the Backend Directory:
cd path/backend

2. Build the backend (Compile & Package):
mvn clean install

3. Run the Spring Boot Application:
java -jar target/movieapp-0.0.1-SNAPSHOT.jar
(note that target/movieapp might be slightly different when you run it, but will be named similarly)

After running this, the backend will be available at `http://localhost:8080`.

---

## Running the Frontend Manually
If you want to start the frontend without using the script:

1. Navigate to the Frontend Directory:
cd path/frontend

2. Install Dependencies (only needed the first time):
npm install

3. Start the Frontend:
npm run dev

This will run the frontend at `http://localhost:5173/`.

---

# Backend Structure
The Spring Boot backend follows a layered architecture:

backend/
│── src/
│   ├── main/
│   │   ├── java/com/example/movieapp/
│   │   │   ├── controller/      (Handles API requests)
│   │   │   │   ├── MovieController.java  
│   │   │   │   ├── ReviewController.java  
│   │   │   │   ├── AddressController.java  
│   │   │   │   ├── PaymentCardController.java  
│   │   │   │   ├── CustomerController.java  
│   │   │   │   ├── AdminController.java  
│   │   │   ├── service/         (Contains business logic)
│   │   │   │   ├── MovieService.java  
│   │   │   │   ├── ReviewService.java  
│   │   │   │   ├── AddressService.java  
│   │   │   │   ├── PaymentCardService.java  
│   │   │   │   ├── CustomerService.java  
│   │   │   │   ├── AdminService.java  
│   │   │   ├── repository/      (Handles database queries)
│   │   │   │   ├── MovieRepository.java  
│   │   │   │   ├── ReviewRepository.java  
│   │   │   │   ├── AddressRepository.java  
│   │   │   │   ├── PaymentCardRepository.java  
│   │   │   │   ├── CustomerRepository.java  
│   │   │   │   ├── AdminRepository.java  
│   │   │   ├── model/           (Defines entity models)
│   │   │   │   ├── Movie.java  
│   │   │   │   ├── Review.java  
│   │   │   │   ├── Address.java  
│   │   │   │   ├── MPAARating.java  (Enum for ratings)
│   │   │   │   ├── PaymentCard.java  
│   │   │   │   ├── Customer.java  
│   │   │   │   ├── User.java  
│   │   │   │   ├── Admin.java  
│   │   │   │   ├── Status.java  (Enum for customer status)
│   │   │   │   ├── Role.java  (Enum for user roles)
│   │   │   ├── util/            (Utility classes)
│   │   │   │   ├── EncryptionUtil.java  
│   │   │   ├── MovieappApplication.java    (Main entry point)
│   │   ├── resources/
│   │   │   ├── application.properties      (Database & Spring settings)
│── pom.xml                                  (Project dependencies)




---

## Backend Explanation
| Layer       | File                          | Purpose                                  |
|------------|------------------------------|------------------------------------------|
| Main       | MovieappApplication.java      | Starts the application                   |
| Controller | MovieController.java          | Handles API requests for movies          |
| Controller | ReviewController.java         | Handles API requests for reviews         |
| Controller | AddressController.java        | Handles API requests for addresses       |
| Controller | PaymentCardController.java    | Handles API requests for payment cards   |
| Controller | CustomerController.java       | Handles API requests for customers       |
| Controller | AdminController.java          | Handles API requests for admins          |
| Service    | MovieService.java             | Business logic for movies                |
| Service    | ReviewService.java            | Business logic for reviews               |
| Service    | AddressService.java           | Business logic for addresses             |
| Service    | PaymentCardService.java       | Business logic for payment cards         |
| Service    | CustomerService.java          | Business logic for customers             |
| Service    | AdminService.java             | Business logic for admins                |
| Repository | MovieRepository.java          | Database access for movies               |
| Repository | ReviewRepository.java         | Database access for reviews              |
| Repository | AddressRepository.java        | Database access for addresses            |
| Repository | PaymentCardRepository.java    | Database access for payment cards        |
| Repository | CustomerRepository.java       | Database access for customers            |
| Repository | AdminRepository.java          | Database access for admins               |
| Model      | Movie.java                    | Defines `Movie` object                   |
| Model      | Review.java                   | Defines `Review` object                  |
| Model      | Address.java                  | Defines `Address` object                 |
| Model      | MPAARating.java               | Enum for MPAA ratings                    |
| Model      | PaymentCard.java              | Defines `PaymentCard` object with encryption logic |
| Model      | Customer.java                 | Defines `Customer` object extending `User` |
| Model      | User.java                     | Defines `User` base class                |
| Model      | Admin.java                    | Defines `Admin` as a subtype of `User`   |
| Model      | Role.java                     | Enum for user roles (Admin, Customer)    |
| Model      | Status.java                   | Enum for customer statuses               |
| Util       | EncryptionUtil.java           | Handles encryption & decryption logic    |




Spring Boot automatically scans these components and connects them.

---

# API Endpoints & Usage
Base URL: `http://localhost:8080`

## This backend provides REST API endpoints to interact with movies, reviews, addresses, customers, and payment cards.

### 🎬 Movies Endpoints
| Method     | Endpoint                              | Request Body (if needed)                                     | Description                             |
|------------|---------------------------------------|--------------------------------------------------------------|-----------------------------------------|
| **GET**    | `/api/movies`                         | None                                                         | Fetches all movies                      |
| **GET**    | `/api/movies/{id}`                    | None                                                         | Fetches a specific movie by ID          |
| **GET**    | `/api/movies/search/title/{title}`    | None                                                         | Fetches movies by title                 |
| **GET**    | `/api/movies/search/genre/{genre}`    | None                                                         | Fetches movies by genre                 |
| **GET**    | `/api/movies/search/cast/{cast}`      | None                                                         | Fetches movies by cast member           |
| **GET**    | `/api/movies/search/director/{director}` | None                                                      | Fetches movies by director              |
| **GET**    | `/api/movies/search/producer/{producer}` | None                                                      | Fetches movies by producer              |
| **GET**    | `/api/movies/search/rating/{mpaa}`    | None                                                         | Fetches movies by MPAA rating           |
| **GET**    | `/api/movies/search`                  | Query params: `?title=Inception&genre=Action&mpaa=PG-13`     | Flexible search by any combination      |
| **POST**   | `/api/movies`                         | `{ "title": "Movie", "genre": "Action Epic", "cast": "Actor Names", "director": "Director Names", "producer": "Producer Names", "synopsis": "Brief description of the movie", "picture": "image_path", "video": "youtube link", "mpaa": "PG-13" }`      | Adds a new movie                        |
| **PUT**    | `/api/movies/{id}`                    | `{ "title": "Updated Movie Title", "genre": "Comedy", "cast": "New Actor Names", "director": "New Directors", "producer": "New Producers", "synopsis": "Updated description", "picture": "image_path", "video": "updated_trailer.mp4", "mpaa": "R" }`            | Updates an existing movie               |
| **DELETE** | `/api/movies/{id}`                    | None                                                         | Deletes a movie                         |

For anything that has a request body for movies, all of them are optional meaning you can include or not include them in the request body 
and then will simply be ignored if not included. It is very important that when setting an MPAA rating that you stick to {G, PG, PG13,
R, NC17} exactly as shown here as the Enum is very strict about this. You must also make sure when adding multiple cast members, directors,
and/or producers for any one movie that you list them out under their respective attribute as such "Jacob Cromer, Maria Khambati" where each
name is separated by a comma and a space ", " as that is how they are passed to create arrays of people to be queried later.

### 📝 Reviews Endpoints
| Method     | Endpoint                              | Request Body (if needed)                                     | Description                             |
|------------|---------------------------------------|--------------------------------------------------------------|-----------------------------------------|
| **GET**    | `/api/movies/{id}/reviews`           | None                                                         | Fetches all reviews for a movie by ID   |
| **GET**    | `/api/movies/search/reviews`         | Query params: `?title=Inception`                             | Fetches all reviews for a movie by title |
| **POST**   | `/api/reviews/movie/{id}`            | `{ "reviewerName": "John", "rating": 5, "comment": "Great movie!" }` | Adds a review for a movie by ID |
| **POST**   | `/api/reviews/movie/title/{title}`   | `{ "reviewerName": "John", "rating": 5, "comment": "Great movie!" }` | Adds a review for a movie by title |
| **DELETE** | `/api/reviews/{id}`                  | None                                                         | Deletes a review for a movie by review ID |

The review endpoints are rather self explanatory. None of the three parameters can be left out when creating a review, and rating must be an integer between 1 and 5.
Whatever movie you want to add a review for, you must make sure you include its title or ID in the URL depending on which endpoint you are using.

### 📍 Addresses Endpoints
| Method     | Endpoint               | Request Body (if needed)  | Description                     |
|------------|------------------------|---------------------------|---------------------------------|
| **GET**    | `/api/addresses`       | None                      | Fetches all addresses          	|
| **GET**    | `/api/addresses/{id}`  | None                      | Fetches an address by ID        |
| **DELETE**    | `/api/addresses/{id}`| None                     | Deletes an address by ID        |

I believe the address IDs are a little unecessary, but they are there should you need them for any reason. These can also be accessed through a GET request on
a specific customer.


### 💳 Payment Card Endpoints
| Method     | Endpoint                                      | Request Body (if needed)                                            | Description                                   |
|------------|---------------------------------------------|-------------------------------------------------------------------|-----------------------------------------------|
| **GET**    | `/api/payment-cards`                       | None                                                              | Fetches all payment cards                     |
| **GET**    | `/api/payment-cards/{id}`                  | None                                                              | Fetches a specific payment card by ID         |
| **GET**    | `/api/payment-cards/customer/{id}`         | None                                                              | Fetches all payment cards for a specific customer ID|
| **POST**   | `/api/payment-cards/customer/{customerId}` | `{ "decryptedCardNumber": "1234567812345678", "expirationDate": "2026-12-31", "decryptedCvv": "123" }` | Adds a new card using the customer's registered address |
| **POST**   | `/api/payment-cards/customer/{customerId}/new-address` | `{ "paymentCard": { "decryptedCardNumber": "9876543210987654", "expirationDate": "2025-11-30", "decryptedCvv": "456" }, "billingAddress": { "street": "456 Elm St", "city": "Los Angeles", "state": "CA", "zipCode": "90001", "country": "USA" } }` | Adds a new payment card with a new billing address |
| **DELETE** | `/api/payment-cards/{id}`                  | None                                                              | Deletes a payment card by ID                  |
| **GET**    | `/api/payment-cards/{id}/decrypt-card`     | None                                                              | Retrieves the decrypted card number (secure use only) |
| **GET**    | `/api/payment-cards/{id}/decrypt-cvv`      | None                                                              | Retrieves the decrypted CVV (secure use only) |

Payment cards can be a little strict as well. Make sure that you keep card numbers at 16 digits and CVVs at 3. They allow for a little more than that actually, but that was just 
a safety measure. You will receive an error message (not an uncaught error) if you try and insert a card to a customer that already has 3, as that is one of the requirements of the
project. Again, this will not crash the system, but it will notify you if you try it. Lastly, for the dates we are using a specific library that expects this date format of
YYYY-MM-DD so please stick to that religiously. States can hold more characters than two but for consistency, let\'s stick to the two-letter representation and just keep all payment
country values as 'USA'.java

You can see there are two different POST endpoints here. One allows you to add a card to a customer using their address attribute as the billing address, and the other asks you
to specify one. Be aware of the slightly different structures of the request bodies for these two endpoints. The cards also manage addresses well by checking to make sure that
the address doesn\'t already exist in the database. If so, it will just grab its address ID and use that, so no need to worry about that.

### 📝 Customer Endpoints
| Method     | Endpoint                                      | Request Body (if needed)                                            | Description                                   |
| **POST**   | /api/customers                                | `{ "firstName": "John", "lastName": "Doe", "email": "john@example.com", "decryptedPassword": "password123", "role" : "ACTIVE", "isSubscriber": true, "address": { "street": "123 Main St", "city": "New York", "state": "NY", "zipCode": "10001", "country": "USA" }`                                           | Creates a new customer                                  |
| **GET**     | /api/customers                               | None                                            | Fetches all customers                                  | 
| **GET**     | /api/customers/{id}                          | None                                            | Fetches a customer by ID                               | 
| **GET**     | /api/customers/email/{email}                 | None                                            | Fetches a customer by email                            |
| **PUT**     | /api/customers/{id}                          | `{ "firstName": "John", "lastName": "Smith", "decryptedPassword": "newpass", "isSubscriber": false, "status": "INACTIVE", "address": { "street": "456 Elm St", "city": "Los Angeles", "state": "CA", "zipCode": "90001", "country": "USA" } }`                                  | Updates customer details (besides email)               | 
| **DELETE**  | /api/customers/{id}                          | None                                            | Deletes a customer by ID                               | 
| **DELETE**  | /api/customers/email/{email}                 | None                                            | Deletes a customer by email                            | 

Customers and admins are likely the most important endpoints here. When creating a customer with a POST request, all of the above attributes and values are completely necessary 
other than role. Role is defaultly set to ACTIVE if you don\'t specify but will accept (and only accept) {ACTIVE, INACTIVE, SUSPENDED}. Also, don\'t bother trying to
set status. It is also defaulty set and any attempts to change it will not work. The system also automatically handles
duplicate email addresses, so if you try and insert a duplicate email (checking admin and customer) it will give you an error response. When updating a customer, all of the attributes, again,
are unecessary. You can include or leave out any of them you wish to (if you update an address, I would always update the full address) Pulling a customer will give you all of 
their personal info, an array of payment cards, and some attributes of when they were last updated, when they were created, last login, and last logout as well. You do not ever have
to update these last four as those are completely managed by the system and just for record keeping. Deleting a customer will also delete all of their payment cards.

### 📝 Admin Endpoints

| Method     | Endpoint                                      | Request Body (if needed)                                            | Description                                   |
| **POST**   | /api/admins                                   | { "firstName": "Admin", "lastName": "User", "email": "admin@example.com", "decryptedPassword": "securepassword" }                                          | Creates a new admin                                  |
| **GET**     | /api/admins                                  | None                                            | Fetches all admins                            | 
| **GET**     | /api/admins/{id}                             | None                                            | Fetches an admin by ID                        |  
| **GET**     | /api/admins/email/{email}                    | None                                            | Fetches an admin be email                     | 
| **PUT**     | /api/admins/{id}                             | { "firstName": "Admin", "lastName": "Updated", "decryptedPassword": "newsecurepassword" }                                           | Updates admin details (besides email)                          | 
| **DELETE**  | /api/admins/{id}                             | None                                            | Deletes an admin by ID                        | 
| **DELETE**  | /api/admins/email/{email}                    | None                                            | Deletes an admin by email                     | 


The admin table is basically the exact same as the customer table with a couple less attributes. The only real reason they exist as separate tables is to not have a bunch
of null values for admin rows in a user table. The role attribute for customers and admins serves as like a badge for different user types. We will work out logging in/registration
kinks very soon.

---

# Summary
Run everything together:
./start_project.sh

Stop everything:
./stop_project.sh (closing the frontend and backend terminals should also work)

Run backend manually:
mvn clean install 
java -jar target/movieapp-0.0.1-SNAPSHOT.jar

Run frontend manually:
npm install (only once)
npm run dev

(note if you are using my version of the scripts, I am emulating a Linux terminal on a Windows machine. That means in some cases 
	I have to use Linux commands that emulate Windows commands (I know), so you may have to configure the scripts slightly for use 
	on an actual Linux machine or for MacOS. If that is the case, please create separate scripts if you must and call them start/stop_project_mac.sh 
	that way when we continue sharing in the future, both options are always there!)

---

Given that the project was refactored in Linux terminal and not IntelliJ, I am no longer using JDBC/ConnecterJ. 
That means you no longer need these and instead all dependencies are handled by Maven, which automatically factors in dependencies (in pom.xml) 
and builds/compiles the project freshly each time using mvn clean install. For SpringBoot, no installations are necessary. 
However, you will have to install Apache Maven to run the backend. Unfortunately this is the easiest way for a big backend project like this, 
but installation is quick and easy.

Just download Maven from the official Apache Maven site.

You can check the installation using:
mvn -version which should give you a similar output to the following:


Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: C:\Program Files\Apache\apache-maven-3.9.9
Java version: 17.0.4, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-17.0.4
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

Keep in mind this project is running in Java 17, specifically 17.0.4.

Anyone in the frontend running the project should already have node.js installed but can check with:
node -v
npm -v












