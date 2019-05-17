package ru.eremin.tm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@Configuration
@Import(DataSourceConfiguration.class)
public class AppConfiguration {
}
