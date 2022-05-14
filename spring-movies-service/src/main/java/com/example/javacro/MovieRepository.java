package com.example.javacro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

interface MovieRepository extends CrudRepository<Movie, Long> {
    Streamable<Movie> findAll();
}
