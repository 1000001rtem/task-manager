package ru.eremin.tm.client;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.config.AppClientConfiguration;


/**
 * Hello world!
 */
public class Application {

    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(AppClientConfiguration.class);
        @NotNull final ServiceLocator bootstrap = context.getBean(ServiceLocator.class);
        @NotNull final Thread thread = new Thread(bootstrap::closeSession);
        Runtime.getRuntime().addShutdownHook(thread);
        bootstrap.init();
    }

}
