package com.example.javacro;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JavacroBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavacroBackendApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    ApplicationRunner applicationRunner(MovieRepository movieRepository) {
        return args -> {
            movieRepository.save(new Movie("Fight Club", "https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg"));
            movieRepository.save(new Movie("Bourne Identity", "https://m.media-amazon.com/images/M/MV5BM2JkNGU0ZGMtZjVjNS00NjgyLWEyOWYtZmRmZGQyN2IxZjA2XkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg"));
            movieRepository.save(new Movie("Independence Day", "https://m.media-amazon.com/images/I/51+wqGIDRJL._AC_.jpg"));
        };
    }
}

