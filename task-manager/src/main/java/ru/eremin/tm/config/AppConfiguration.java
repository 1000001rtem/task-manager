package ru.eremin.tm.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.eremin.tm.endpoint.ProjectEndpointImpl;
import ru.eremin.tm.endpoint.TaskEndpointImpl;
import ru.eremin.tm.endpoint.UserEndpointImpl;

import javax.xml.ws.Endpoint;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@Configuration
@Import(DataSourceConfiguration.class)
@EnableJpaRepositories("ru.eremin.tm.repository")
public class AppConfiguration {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public ProjectEndpointImpl projectEndpointImpl() {
        return new ProjectEndpointImpl();
    }

    @Bean
    public Endpoint projectEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), projectEndpointImpl());
        endpoint.publish("/services/projectEndpoint");
        return endpoint;
    }

    @Bean
    public TaskEndpointImpl taskEndpointImpl() {
        return new TaskEndpointImpl();
    }

    @Bean
    public Endpoint taskEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), taskEndpointImpl());
        endpoint.publish("/services/taskEndpoint");
        return endpoint;
    }

    @Bean
    public UserEndpointImpl userEndpointImpl() {
        return new UserEndpointImpl();
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userEndpointImpl());
        endpoint.publish("/services/userEndpoint");
        return endpoint;
    }

}
