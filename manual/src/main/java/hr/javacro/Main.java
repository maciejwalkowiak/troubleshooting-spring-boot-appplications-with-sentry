package hr.javacro;

import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Sentry.init(opts -> {
            opts.setDsn("https://7561d36ce9ea4265abf4a77deb78af3a@o420886.ingest.sentry.io/6389805");
            opts.setDebug(true);
        });
        var conference = new Conference(1L, null);

        try {
            LOGGER.info("Hello JavaCro!");
            System.out.println(conference.name().toLowerCase());
            party(conference);
        } catch (Exception e) {
            Sentry.captureException(e);
            LOGGER.error("Ooops", e);
        }
    }

    private static void party(Conference conference) {
        throw new IllegalStateException("too much party");
    }
}

