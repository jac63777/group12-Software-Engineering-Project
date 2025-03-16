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
>[!WARNING]
> Scripts ONLY works for WINDOWS systems, start the backend and frontend manually for other systems.
```sh
./start_project.sh
```
To **stop** the Project
```sh
./stop_project.sh
```

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
`cd path/backend`

2. Build the backend (Compile & Package):
`mvn clean install`

3. Run the Spring Boot Application:
`java -jar target/movieapp-0.0.1-SNAPSHOT.jar`
(note that target/movieapp might be slightly different when you run it, but will be named similarly)

After running this, the backend will be available at `http://localhost:8080`.

---

## Running the Frontend Manually
If you want to start the frontend without using the script:

1. Navigate to the Frontend Directory:
`cd path/frontend`

2. Install Dependencies (only needed the first time):
`npm install`

3. Start the Frontend:
`npm run dev`

This will run the frontend at `http://localhost:5173/`.

---

# Backend Structure
The Spring Boot backend follows a layered architecture:

```
backend/
│── src/
│   ├── main/
│   │   ├── java/com/example/movieapp/
│   │   │   ├── controller/      (Handles API requests)
│   │   │   │   ├── MovieController.java  
│   │   │   │   ├── ReviewController.java  
│   │   │   ├── service/         (Contains business logic)
│   │   │   │   ├── MovieService.java  
│   │   │   │   ├── ReviewService.java  
│   │   │   ├── repository/      (Handles database queries)
│   │   │   │   ├── MovieRepository.java  
│   │   │   │   ├── ReviewRepository.java  
│   │   │   ├── model/           (Defines entity models)
│   │   │   │   ├── Movie.java  
│   │   │   │   ├── Review.java  
│   │   │   │   ├── MPAARating.java  (Enum for ratings)
│   │   │   ├── MovieappApplication.java    (Main entry point)
│   │   ├── resources/
│   │   │   ├── application.properties      (Database & Spring settings)
│── pom.xml                                  (Project dependencies)
```

---

## Backend Explanation
| Layer       | File                      | Purpose                        |
|-------------|---------------------------|--------------------------------|
| Main        | MovieappApplication.java  | Starts the app                 |
| Controller  | MovieController.java      | Handles API requests for movies |
| Controller  | ReviewController.java     | Handles API requests for reviews |
| Service     | MovieService.java         | Business logic for movies       |
| Service     | ReviewService.java        | Business logic for reviews      |
| Repository  | MovieRepository.java      | Database access for movies      |
| Repository  | ReviewRepository.java     | Database access for reviews     |
| Model       | Movie.java                | Defines Movie object           |
| Model       | Review.java               | Defines Review object          |
| Model       | MPAARating.java           | Enum for MPAA ratings          |


Spring Boot automatically scans these components and connects them.

---

# API Endpoints & Usage
Base URL: `http://localhost:8080`

This backend provides REST API endpoints to interact with movies.

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
| **POST**   | `/api/movies`                         | `{ "title": "Movie", "genre": "Action", "year": 2024 }`      | Adds a new movie                        |
| **PUT**    | `/api/movies/{id}`                    | `{ "title": "Updated Title", "genre": "Comedy" }`            | Updates an existing movie               |
| **DELETE** | `/api/movies/{id}`                    | None                                                         | Deletes a movie                         |
| **GET**    | `/api/movies/{id}/reviews`            | None                                                         | Fetches all reviews for a movie by ID   |
| **GET**    | `/api/movies/search/reviews`          | Query params: `?title=Inception``                            | Fetches all reviews for a movie by title|
| **GET**    | `/api/movies/search/producers`        | None                                                         | Fetches all unique producers            |

NOTE, you may search by any combination of MPAA Rating, Title, Genre, Director, Cast. Useful for querying based on multiple parameters.

---

## Example API Calls

Note, of course, that the endpoints can always be checked for what they return by putting them into the browser, 
which will return a page of the JSON or whatever response.

**Response**
{"message":"Welcome to the Movie API!"}

### Fetch All Movies
**Request**
GET http://localhost:8080/api/movies

**Response**
[ { "title": "Inception", "year": 2010 }, { "title": "Interstellar", "year": 2014 } ] (an array of movies all with the same structure)

### Fetch a Movie by Title
GET http://localhost:8080/api/movies/title/Inception <br>
or GET http://localhost:8080/api/movies/search?title=Inception

**Response**
{ "title": "Inception", "year": 2010 }

### Add a New Movie
**Request**
POST http://localhost:8080/api/movies Content-Type: application/json <br>
**Body**
{ "title": "The Matrix", "year": 1999 }

**Response**
{ "title": "The Matrix", "year": 1999 }

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












