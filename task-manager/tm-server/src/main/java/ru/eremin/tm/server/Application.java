package ru.eremin.tm.server;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.bootstrap.Bootstrap;
import ru.eremin.tm.server.bootstrap.ServiceLocator;
import ru.eremin.tm.server.endpoint.*;

import javax.enterprise.inject.se.SeContainerInitializer;


/**
 * Hello world!
 */
public class Application {

    private static final Class[] CLASSES = {
            AuthorizationEndpoint.class, ProjectEndpoint.class, AdminEndpoint.class,
            UserEndpoint.class, SessionEndpoint.class
    };

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(Application.class)
                .initialize()
                .select(Bootstrap.class).get().init(CLASSES);
    }

}
