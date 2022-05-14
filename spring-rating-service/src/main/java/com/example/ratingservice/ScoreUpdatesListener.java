package com.example.ratingservice;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.sentry.Sentry;
import io.sentry.protocol.Request;
import io.sentry.spring.tracing.SentryTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
class ScoreUpdatesListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreUpdatesListener.class);
    private final RatingRepository ratingRepository;

    public ScoreUpdatesListener(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @RabbitListener(queues = "score-updates")
    @SentryTransaction(name = "ScoreUpdatesListener", operation = "amqp")
    void handleScoreUpdate(Message message, @Payload ScoreUpdate scoreUpdate) {
        Sentry.configureScope(scope -> {
            Request request = new Request();
            request.setData(scoreUpdate);
            request.setUrl(message.getMessageProperties().getReceivedExchange() + "/" + message.getMessageProperties().getConsumerQueue());
            request.setHeaders(convert(message.getMessageProperties().getHeaders()));
            scope.setTag("routingKey", message.getMessageProperties().getReceivedRoutingKey());
            scope.setRequest(request);
        });
        LOGGER.info("Received message: {}", message.getMessageProperties().getMessageId());
        LOGGER.error("something went wrong", new RuntimeException());
        ratingRepository.saveRating(scoreUpdate.movieId(), scoreUpdate.score());
    }

    Map<String, String> convert(Map<String, Object> map) {
        Map<String, String> result = new HashMap<>();
        map.forEach((key, value) -> result.put(key, String.valueOf(value)));
        return result;
    }

    static record ScoreUpdate(@JsonProperty("movieId") Long movieId, @JsonProperty("score") Integer score){}

}