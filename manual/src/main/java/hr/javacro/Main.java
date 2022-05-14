package hr.javacro;

import io.sentry.HubAdapter;
import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Sentry.init(opts -> {
            opts.setDsn("https://7561d36ce9ea4265abf4a77deb78af3a@o420886.ingest.sentry.io/6389805");
            opts.setDebug(true);
            opts.setRelease("1.2");
            opts.addContextTag("userId");
        });
        var conference = new Conference(1L, null);

        Sentry.configureScope(scope -> {
            scope.setContexts("cli", "foo bar");
        });

        MDC.put("userId", "maciej");

        try {
            LOGGER.info("Hello JavaCro!!23");
//            System.out.println(conference.name().toLowerCase());
//            party(conference);
            throw new IllegalStateException("foooobar!");
        } catch (Exception e) {
            LOGGER.error("Ooops", e);
        }


    }

    private static void party(Conference conference) {
        throw new IllegalStateException("too much party");
    }
}

