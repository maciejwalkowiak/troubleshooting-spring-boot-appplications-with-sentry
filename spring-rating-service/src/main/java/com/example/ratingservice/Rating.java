package com.example.ratingservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rating(@JsonProperty Long movieId, @JsonProperty Integer rating) {
}
