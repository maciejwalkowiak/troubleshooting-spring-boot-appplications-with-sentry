package com.example.javacro;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
class MovieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;
    private final RatingService ratingService;
    private final Random random = new Random();

    public MovieService(MovieRepository movieRepository, RatingService ratingService) {
        this.movieRepository = movieRepository;
        this.ratingService = ratingService;
    }

    List<MovieDTO> movies() {
        LOGGER.info("Fetching movies from the repository");
        // fail randomly 33% of times
        if (random.nextInt() % 3 == 0) {
            throw new BusinessException(BusinessException.Code.BP_2, "something went wrooong");
        }
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
