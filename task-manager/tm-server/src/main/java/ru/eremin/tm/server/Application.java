package ru.eremin.tm.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eremin.tm.server.bootstrap.ServiceLocator;
import ru.eremin.tm.server.config.AppConfiguration;


/**
 * Hello world!
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final ServiceLocator locator = context.getBean(ServiceLocator.class);
        locator.init();
    }

}
