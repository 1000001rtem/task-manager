package ru.eremin.tm.server.config;


import lombok.SneakyThrows;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.Nullable;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @autor av.eremin on 24.04.2019.
 */

public final class SqlSessionConfig {

    @SneakyThrows
    public static SqlSessionFactory getSessionFactory() {
        Properties properties = getProperties();
        @Nullable final String user = properties.getProperty("jdbc.username");
        @Nullable final String password = properties.getProperty("jdbc.password");
        @Nullable final String url = properties.getProperty("jdbc.url");
        @Nullable final String driver = properties.getProperty("jdbc.driver");
        final DataSource dataSource = new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        final Environment environment = new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(ProjectRepository.class);
        configuration.addMapper(SessionRepository.class);
        configuration.addMapper(UserRepository.class);
        configuration.addMapper(TaskRepository.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(SqlSessionConfig.class.getClassLoader().getResourceAsStream("db.properties"));
        return properties;
    }

}
