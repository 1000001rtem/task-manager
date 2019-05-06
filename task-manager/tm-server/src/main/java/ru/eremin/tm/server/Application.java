package ru.eremin.tm.server;

import ru.eremin.tm.server.bootstrap.Bootstrap;
import ru.eremin.tm.server.endpoint.*;

import javax.enterprise.inject.se.SeContainerInitializer;


/**
 * Hello world!
 */
public class Application {

    private static final Class[] CLASSES = {
            TaskEndpoint.class, AuthorizationEndpoint.class, ProjectEndpoint.class, AdminEndpoint.class,
            UserEndpoint.class, SessionEndpoint.class
    };

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(Application.class)
                .initialize()
                .select(Bootstrap.class).get().init();
    }

}
