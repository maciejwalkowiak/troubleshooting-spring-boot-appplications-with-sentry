package com.example.javacro;

import io.sentry.spring.tracing.SentrySpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class MovieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;
    private final RatingService ratingService;

    public MovieService(MovieRepository movieRepository, RatingService ratingService) {
        this.movieRepository = movieRepository;
        this.ratingService = ratingService;
    }

    // adding @SentrySpan annotation on the method level, turns invocation of this method into a span.
    @SentrySpan
    List<MovieDTO> movies() {
        LOGGER.info("Loading movies");
        return movieRepository.findAll()
                .map(this::findRating)
                .map(this::toDto)
                .toList();
    }

    private MovieDTO toDto(Pair<Movie, Optional<Rating>> pair) {
        return MovieDTO.create(pair.getFirst(), pair.getSecond());
    }

    private Pair<Movie, Optional<Rating>> findRating(Movie movie) {
        return Pair.of(movie, Optional.ofNullable(ratingService.findRating(movie.getId())));
    }
}
