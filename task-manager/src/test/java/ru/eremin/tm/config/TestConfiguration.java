package ru.eremin.tm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@Configuration
@ComponentScan("ru.eremin.tm")
@Import(TestDataSourceConfiguration.class)
@EnableJpaRepositories("ru.eremin.tm.repository")
public class TestConfiguration {
}
