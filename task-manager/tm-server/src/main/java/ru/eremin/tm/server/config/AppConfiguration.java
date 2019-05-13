package ru.eremin.tm.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @autor av.eremin on 13.05.2019.
 */

@Configuration
@ComponentScan("ru.eremin.tm.server")
@Import(DataSourceConfiguration.class)
@EnableJpaRepositories("ru.eremin.tm.server.repository")
public class AppConfiguration {
}
