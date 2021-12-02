package hr.javacro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var conference = new Conference(1L, null);

        try {
            LOGGER.info("Hello JavaCro!");
            System.out.println(conference.name().toLowerCase());
            party(conference);
        } catch (Exception e) {
            LOGGER.error("Ooops", e);
        }
    }

    private static void party(Conference conference) {
        throw new IllegalStateException("too much party");
    }
}

