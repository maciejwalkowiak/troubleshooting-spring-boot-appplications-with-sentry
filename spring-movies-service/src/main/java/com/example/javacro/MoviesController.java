package com.example.javacro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

// all Spring MVC request executions are automatically turned into Sentry transactions
@RestController
@CrossOrigin
public class MoviesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoviesController.class);
    private final Random random = new Random();
    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    List<MovieDTO> movies() {
        LOGGER.info("Loading movies");
        // fail randomly 33% of times
        if (random.nextInt() % 3 == 0) {
            throw new IllegalStateException("something went wrooong");
        }
        return movieService.movies();
    }
}
