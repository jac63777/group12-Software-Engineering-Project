package com.example.movieapp.service;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Review;
import com.example.movieapp.model.MPAARating;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    // ✅ Search movies using multiple filters
    public List<Movie> searchMovies(Integer id, String title, String genre, String cast,
                                    String director, String producer, String mpaaRating) {
        MPAARating mpaaEnum = null;
        if (mpaaRating != null) {
            try {
                mpaaEnum = MPAARating.valueOf(mpaaRating.toUpperCase());
            } catch (IllegalArgumentException ignored) {
                // If the rating is invalid, keep it null to avoid errors
            }
        }
        return movieRepository.searchMovies(id, title, genre, cast, director, producer, mpaaEnum);
    }

    // ✅ Fetch a single movie by ID and load its reviews
    public Optional<Movie> getMovieById(int movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        movie.ifPresent(m -> m.setReviews(reviewRepository.findByMovieId(movieId)));
        return movie;
    }

    // ✅ Fetch all movies and load reviews for each movie
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> movie.setReviews(reviewRepository.findByMovieId(movie.getId())));
        return movies;
    }

    // ✅ Search movies by title
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    // ✅ Search movies by genre
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    // ✅ Search movies by cast member
    public List<Movie> getMoviesByCast(String cast) {
        return movieRepository.findByCastContainingIgnoreCase(cast);
    }

    // ✅ Search movies by director
    public List<Movie> getMoviesByDirector(String director) {
        return movieRepository.findByDirectorContainingIgnoreCase(director);
    }

    // ✅ Search movies by producer
    public List<Movie> getMoviesByProducer(String producer) {
        return movieRepository.findByProducerContainingIgnoreCase(producer);
    }

    // ✅ Search movies by MPAA rating (Fix: Convert String to Enum)
    public List<Movie> getMoviesByRating(String mpaa) {
        try {
            MPAARating ratingEnum = MPAARating.valueOf(mpaa.toUpperCase());
            return movieRepository.findByMpaa(ratingEnum);
        } catch (IllegalArgumentException e) {
            return List.of(); // Return empty list if the rating is invalid
        }
    }

    // ✅ Add a new movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // ✅ Update a movie (Fix: Null-safe field updates)
    public Movie updateMovie(int id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            if (updatedMovie.getTitle() != null) movie.setTitle(updatedMovie.getTitle());
            if (updatedMovie.getGenre() != null) movie.setGenre(updatedMovie.getGenre());
            if (updatedMovie.getCast() != null) movie.setCast(updatedMovie.getCast());
            if (updatedMovie.getDirector() != null) movie.setDirector(updatedMovie.getDirector());
            if (updatedMovie.getProducer() != null) movie.setProducer(updatedMovie.getProducer());
            if (updatedMovie.getSynopsis() != null) movie.setSynopsis(updatedMovie.getSynopsis());
            if (updatedMovie.getPicture() != null) movie.setPicture(updatedMovie.getPicture());
            if (updatedMovie.getVideo() != null) movie.setVideo(updatedMovie.getVideo());
            if (updatedMovie.getMpaa() != null) movie.setMpaa(updatedMovie.getMpaa());
            return movieRepository.save(movie);
        }).orElse(null);
    }

    // ✅ Delete a movie
    public boolean deleteMovie(int movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return true;
        }
        return false;
    }

    // ✅ Get all reviews for a movie by ID
    public List<Review> getReviewsForMovieById(int movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    // ✅ Get all reviews for a movie by Title (Fix: Optimized query)
    public List<Review> getReviewsForMovieByTitle(String title) {
        Optional<Movie> movie = movieRepository.findFirstByTitleContainingIgnoreCase(title);
        return movie.map(value -> reviewRepository.findByMovieId(value.getId())).orElseGet(List::of);
    }

    // ✅ Get all distinct producers
    public List<String> getAllProducers() {
        return movieRepository.findAll().stream()
                .map(Movie::getProducer)
                .distinct()
                .collect(Collectors.toList());
    }
}
