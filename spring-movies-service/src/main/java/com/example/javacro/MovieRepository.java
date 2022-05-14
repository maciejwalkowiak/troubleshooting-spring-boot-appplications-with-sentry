package com.example.javacro;

import io.sentry.spring.tracing.SentrySpan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

interface MovieRepository extends CrudRepository<Movie, Long> {
    Streamable<Movie> findAll();
}
