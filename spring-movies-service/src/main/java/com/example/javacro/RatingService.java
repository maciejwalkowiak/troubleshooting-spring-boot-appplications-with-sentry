package com.example.javacro;

import io.sentry.spring.tracing.SentrySpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@SentrySpan
class RatingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingService.class);
    private final RestTemplate restTemplate;

    public RatingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    Rating findRating(Long movieId) {
        LOGGER.info("Finding rating for movie: {}",movieId);
        try {
            return restTemplate.getForObject("http://localhost:8081/rating/" + movieId, Rating.class);
        } catch (Exception e) {
            LOGGER.warn("Failed to find rating for a movie: {}", movieId, e);
            return null;
        }
    }
}
