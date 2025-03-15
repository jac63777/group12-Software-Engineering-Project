package com.example.movieapp.controller;

import com.example.movieapp.model.Review;
import com.example.movieapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // GET /reviews/movie/{movieId} → Fetch all reviews for a movie
    @GetMapping("/movie/{movieId}")
    public List<Review> getReviewsForMovie(@PathVariable int movieId) {
        return reviewService.getReviewsForMovie(movieId);
    }
}