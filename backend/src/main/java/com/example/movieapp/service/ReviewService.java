package com.example.movieapp.service;

import com.example.movieapp.model.Review;
import com.example.movieapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviewsForMovie(int movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
}