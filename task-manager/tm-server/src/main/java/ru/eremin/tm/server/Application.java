package ru.eremin.tm.server;

import ru.eremin.tm.server.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;


/**
 * Hello world!
 */
public class Application {

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(Application.class)
                .initialize()
                .select(Bootstrap.class).get().init();
    }

}
