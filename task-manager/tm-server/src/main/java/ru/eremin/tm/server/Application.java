package ru.eremin.tm.server;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.bootstrap.Bootstrap;
import ru.eremin.tm.server.bootstrap.ServiceLocator;
import ru.eremin.tm.server.endpoint.AuthorizationEndpoint;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;
import ru.eremin.tm.server.endpoint.TaskEndpoint;

/**
 * Hello world!
 */
public class Application {

    private static final Class[] CLASSES = {
            TaskEndpoint.class, AuthorizationEndpoint.class, ProjectEndpoint.class
    };

    public static void main(String[] args) {
        @NotNull final ServiceLocator locator = new Bootstrap();
        locator.init(CLASSES);
    }
}
