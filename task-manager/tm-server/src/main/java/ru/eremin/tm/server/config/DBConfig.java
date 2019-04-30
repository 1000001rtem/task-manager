package ru.eremin.tm.server.config;


import lombok.SneakyThrows;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.model.entity.session.Session;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @autor av.eremin on 24.04.2019.
 */

public final class DBConfig {

    public static EntityManagerFactory getFactory() {
        Properties properties = getProperties();
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, properties.getProperty("jdbc.driver"));
        settings.put(Environment.URL, properties.getProperty("jdbc.url"));
        settings.put(Environment.USER, properties.getProperty("jdbc.username"));
        settings.put(Environment.PASS, properties.getProperty("jdbc.password"));
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    @SneakyThrows
    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.load(DBConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        return properties;
    }

}
