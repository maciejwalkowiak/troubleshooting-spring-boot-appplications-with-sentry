package com.example.javacro;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MoviesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoviesController.class);
    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    List<MovieDTO> movies() {
        LOGGER.info("Loading movies from the movie service");
        return movieService.movies();
    }
}
