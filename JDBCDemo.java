
import java.sql.*;

public class JDBCDemo {

    String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
    String username = "group12";
    String password = "Ckks2025!";

    public static void main(String[] args) {
        Util.loadDriver();

        String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
        String username = "group12";
        String password = "Ckks2025!";

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

        addMovie(newMovie);

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");

            String movieName = "The Lion King";
            String query = "SELECT * FROM movie WHERE title = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, movieName);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {

                        Movie movie = new Movie(
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

                        System.out.println("\nFetched Movie Details:");
                        movie.displayMovie();
                    } else {
                        System.out.println("No movie found with name: " + movieName);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } // try

        displayAllMovies();

        deleteMovieByTitle("Blade Runner 2049");

        displayAllMovies();

    } // main

    public static void displayAllMovies() {
        String query = "SELECT * FROM movie";

        String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
        String username = "group12";
        String password = "Ckks2025!";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Get column count for dynamic printing
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println("\n--------------------------------------------------");

            // Print all movie rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // displayAllMovies

    public static void addMovie(Movie movie) {

        String query = "INSERT INTO movie (title, genre, cast, director, producer, synopsis, reviews, picture, video, mppa, showtimes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
        String username = "group12";
        String password = "Ckks2025!";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = connection.prepareStatement(query)) {

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

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Movie added successfully!");
            } else {
                System.out.println("Failed to add movie.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public static void deleteMovieByTitle(String title) {
            String query = "DELETE FROM movie WHERE title = ?";

            String url = "jdbc:mysql://group12-db-csci4050-spring2025.ct0eym2g4j98.us-east-2.rds.amazonaws.com:3306/group12_db?useSSL=false&allowPublicKeyRetrieval=TRUE";
            String username = "group12";
            String password = "Ckks2025!";

            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setString(1, title);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Movie deleted successfully!");
                } else {
                    System.out.println("No movie found with title: " + title);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

} // JDBCDemo
