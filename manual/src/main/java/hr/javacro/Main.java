package hr.javacro;

import io.sentry.Sentry;
import io.sentry.protocol.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Sentry.init(options -> {
            // replace dsn with a dsn specific for your Sentry project
            options.setDsn("https://d5042fdaa80945fcaec38f477ca3779f@o420886.ingest.sentry.io/6001171");
            // When first trying Sentry it's good to see what the SDK is doing:
            options.setDebug(true);
            // by default environment is set to production
            options.setEnvironment("local");
            // setting release makes it easy find out in which release was the error introduced
            // and get an overview of the particular version health
            options.setRelease("1.0.0");
        });
        var conference = new Conference(1L, null);

        Sentry.configureScope(scope -> {
            User user = new User();
            user.setUsername("maciej");
            scope.setUser(user);
        });

        try {
            // with logback configuration in place, all info logs are converted into breadcrumbs that get attached to events.
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

