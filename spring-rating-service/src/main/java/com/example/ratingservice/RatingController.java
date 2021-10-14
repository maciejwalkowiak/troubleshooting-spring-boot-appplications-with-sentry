package com.example.ratingservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
class RatingController {
    private final Random random = new Random();
    private final RatingRepository ratingRepository;

    RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/rating/{movieId}")
    Rating rating(@PathVariable Long movieId) {
        // fail randomly 33% of times
        if (random.nextInt() % 3 == 0) {
            throw new IllegalStateException("something went wrooong");
        }
        var score = ratingRepository.findRating(movieId);
        return new Rating(movieId, score);
    }

}
