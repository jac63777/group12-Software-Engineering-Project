
import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) {
        // setting up JDBC dependencies
        Util.loadDriver();

        // Creating an example movie to add to the database
        Movie newMovie = new Movie(
                0, // Auto-incremented ID, so set to 0 or ignore
                "Blade Runner 2049",
                "Sci-Fi, Neo-Noir",
                "Ryan Gosling, Harrison Ford, Ana de Armas",
                "Denis Villeneuve",
                "Andrew A. Kosove, Broderick Johnson, Bud Yorkin, Cynthia Yorkin",
                "A young blade runner's discovery of a long-buried secret leads him to track down former blade runner Rick Deckard, who's been missing for thirty years.",
                "8.0/10 - IMDb, 81% - Rotten Tomatoes",
                "https://example.com/bladerunner2049.jpg",
                "https://youtube.com/watch?v=gCcx85zbxz4",
                "R",
                null // Showtimes set to null
        );

        // add it to the database
        addMovie(newMovie);

        // search for a movie by title
        String movieName = "The Lion King";
        Movie movie = getMovieByTitle(movieName);

        // display the movie fetched from DB
        if (movie != null) {
            System.out.println("\nFetched Movie Details:");
            movie.displayMovie();
        } // if

        // display all movies to make sure blade runner was added
        displayAllMovies();

        // delete blade runner from the DB
        deleteMovieByTitle("Blade Runner 2049");

        // display all movies to check blade runner was deleted
        displayAllMovies();

    } // main

    // function to display all movies in DB
    public static void displayAllMovies() {
        // creating the query
        String query = "SELECT * FROM movie";

        // DB credentials
        String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
        String username = "group12";
        String password = "Ckks2025!";

        // set up connection and prepare query
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // get column count
            int columnCount = resultSet.getMetaData().getColumnCount();

            // print column names/attributes
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
            } // for

            System.out.println("\n--------------------------------------------------");

            // print query output / all movies
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                } // for
                System.out.println();
            } // while

        } catch (SQLException e) {
            e.printStackTrace();
        } // try

    } // displayAllMovies

    // add a new movie into the database
    public static void addMovie(Movie movie) {

        // prepare query
        String query = "INSERT INTO movie (title, genre, cast, director, producer, synopsis, reviews, picture, video, mppa, showtimes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // DB credentials
        String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
        String username = "group12";
        String password = "Ckks2025!";

        // creating connection and building query object
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // get query inputs from movie object get attribute functions
            // the index corresponds to the question marks in the query String
            pstmt.setString(1, movie.getTitle());
            pstmt.setString(2, movie.getGenre());
            pstmt.setString(3, movie.getCast());
            pstmt.setString(4, movie.getDirector());
            pstmt.setString(5, movie.getProducer());
            pstmt.setString(6, movie.getSynopsis());
            pstmt.setString(7, movie.getReviews());
            pstmt.setString(8, movie.getPicture());
            pstmt.setString(9, movie.getVideo());
            pstmt.setString(10, movie.getMppa());
            pstmt.setString(11, movie.getShowtimes());

            // checking if there was an issue with inserting by checking number of rows
            // inserted into movie table
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Movie added successfully!");
            } else {
                System.out.println("Failed to add movie.");
            } // if

        } catch (SQLException e) {
            e.printStackTrace();
        } // try
    } // addMovie

    // delete a movie from the database
    public static void deleteMovieByTitle(String title) {
        // prepare query
        String query = "DELETE FROM movie WHERE title = ?";

        // DB credentials
        String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
        String username = "group12";
        String password = "Ckks2025!";

        // create a connection and build query object
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, title);

            // check to make sure a row was deleted from the database
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Movie deleted successfully!");
            } else {
                System.out.println("No movie found with title: " + title);
            } // if

        } catch (SQLException e) {
            e.printStackTrace();
        } // try

    } // deleteMovieByTitle

    // query the database for a movie by title and return movie object for queried movie
    public static Movie getMovieByTitle(String movieTitle) {

        // prepare query
        String query = "SELECT * FROM movie WHERE title = ?";

        // DB credentials
        String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
        String username = "group12";
        String password = "Ckks2025!";

        // set up connection and build query object
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, movieTitle);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    // build movie object from query result
                    return new Movie(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("genre"),
                            resultSet.getString("cast"),
                            resultSet.getString("director"),
                            resultSet.getString("producer"),
                            resultSet.getString("synopsis"),
                            resultSet.getString("reviews"),
                            resultSet.getString("picture"),
                            resultSet.getString("video"),
                            resultSet.getString("mppa"),
                            resultSet.getString("showtimes")
                    );
                } else {
                    System.out.println("No movie found with title: " + movieTitle);
                } // if
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } // try

        return null; // return null if no movie is found or an error occurs
        
    } // getMovieByTitle



} // JDBCDemo
