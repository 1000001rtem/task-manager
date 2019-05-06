package ru.eremin.tm.client;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.Bootstrap;
import ru.eremin.tm.client.bootstrap.ServiceLocator;

import javax.enterprise.inject.se.SeContainerInitializer;

/**
 * Hello world!
 */
public class Application {

    public static void main(String[] args) {
        @NotNull final ServiceLocator bootstrap = SeContainerInitializer.newInstance()
                .addPackages(Application.class)
                .initialize()
                .select(Bootstrap.class).get();
        @NotNull final Thread thread = new Thread(bootstrap::closeSession);
        Runtime.getRuntime().addShutdownHook(thread);
        bootstrap.init();
    }

}
