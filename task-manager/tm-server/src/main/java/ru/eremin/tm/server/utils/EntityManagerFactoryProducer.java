package ru.eremin.tm.server.utils;

import ru.eremin.tm.server.config.DBConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * @autor av.eremin on 06.05.2019.
 */

@ApplicationScoped
public class EntityManagerFactoryProducer {

    @PersistenceContext
    private EntityManagerFactory entityManagerFactory;

    @Produces
    public EntityManagerFactory getEntityManagerFactory() {
        return DBConfig.getFactory();
    }

}
