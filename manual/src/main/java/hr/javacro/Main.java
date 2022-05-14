package hr.javacro;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var conference = new Conference(1L, null);

        MDC.put("requestId", UUID.randomUUID().toString());

        try {
            LOGGER.info("Hello JavaCro!!");
            System.out.println(conference.name().toLowerCase());
            party(conference);
            throw new IllegalStateException("foooobar!");
        } catch (Exception e) {
            LOGGER.error("Ooops", e);
        }
    }

    private static void party(Conference conference) {
        throw new IllegalStateException("too much party");
    }
}

