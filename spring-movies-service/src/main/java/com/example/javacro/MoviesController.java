package com.example.javacro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        LOGGER.info("Loading movies");
        return movieService.movies();
    }
}
