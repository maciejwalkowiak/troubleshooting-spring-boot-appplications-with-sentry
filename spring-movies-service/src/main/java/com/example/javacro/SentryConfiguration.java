package com.example.javacro;

import java.util.Map;

import io.sentry.EventProcessor;
import io.sentry.SentryEvent;
import io.sentry.SentryOptions;
import io.sentry.protocol.User;
import io.sentry.spring.SentryUserProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class SentryConfiguration {

    @Bean SentryUserProvider sentryUserProvider() {
        return () -> {
            UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User sentryUser = new User();
            sentryUser.setUsername(user.getUsername());
            sentryUser.setId(String.valueOf(user.getId()));
            sentryUser.setEmail(user.getEmail());
            return sentryUser;
        };
    }

    @Bean EventProcessor eventProcessor() {
        return new EventProcessor() {
            @Override public SentryEvent process(SentryEvent event, Object hint) {
                if (event.getThrowable() instanceof BusinessException e) {
                    String name = e.getBusinessProcessCode().name();
                    event.setTag("business-process-tag", name);
                    event.getContexts().put("business-process-context", Map.of("process", name));
                    event.setExtra("business-process-extra", name);
                }
                return event;
            }
        };
    }

    // @Bean
    SentryOptions.BeforeSendCallback beforeSendCallback() {
        return (sentryEvent, o) -> {
            if (sentryEvent.getThrowable() instanceof BusinessException e && e.getBusinessProcessCode() == BusinessException.Code.BP_2) {
                return null;
            } else {
                return sentryEvent;
            }
        };
    }
}
