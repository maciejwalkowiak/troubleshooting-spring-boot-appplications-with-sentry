package com.example.ratingservice;

import io.sentry.spring.tracing.SentrySpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@SentrySpan
public class RatingRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingRepository.class);
    private final Random random = new Random();
    private final ConcurrentHashMap<Long, Integer> ratings = new ConcurrentHashMap<>();

    Integer findRating(Long movieId) {
        LOGGER.info("Finding rating for movie: {}", movieId);
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var score = ratings.computeIfAbsent(movieId, (id) -> random.nextInt(9) + 1);
        LOGGER.info("Found rating for movie: {}, rating: {}", movieId, score);
        return score;
    }

    void saveRating(Long movieId, Integer score) {
        LOGGER.info("Saving rating for movie: {}, rating: {}", movieId, score);
        ratings.put(movieId, score);
    }
}
