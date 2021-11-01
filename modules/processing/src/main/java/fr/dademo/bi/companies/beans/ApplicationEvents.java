package fr.dademo.bi.companies.beans;

import fr.dademo.bi.companies.tools.FlywayMigrationTools;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
public class ApplicationEvents {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEvents.class);

    @Autowired
    private List<Flyway> flywayMigrations;

    @EventListener(ApplicationStartedEvent.class)
    public void doApplyFlywayMigrations() {

        LOGGER.debug("Application started");
        LOGGER.info("Applying all migrations");
        flywayMigrations.forEach(FlywayMigrationTools::applyMigration);
        LOGGER.info("All migrations applied successfully");
    }
}
