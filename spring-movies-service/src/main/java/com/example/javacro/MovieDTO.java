package com.example.javacro;

import java.util.Optional;

public record MovieDTO(Long id, String title, String thumbnail, Integer rating) {
    static MovieDTO create(Movie movie, Optional<Rating> rating) {
        return new MovieDTO(movie.getId(), movie.getTitle(), movie.getThumbnail(), rating.map(Rating::rating).orElse(null));
    }
}
