package ru.eremin.tm.server;

import ru.eremin.tm.server.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;


/**
 * Hello world!
 */
public class Application {

    public static void main(String[] args) {
        if (args.length > 0 && args[0] != null && !args[0].isEmpty()) System.setProperty("server.port", args[0]);
        SeContainerInitializer.newInstance()
                .addPackages(Application.class)
                .initialize()
                .select(Bootstrap.class).get().init();
    }

}
