package ru.eremin.tm.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.eremin.tm.endpoint.AuthEndpointImpl;
import ru.eremin.tm.endpoint.ProjectEndpointImpl;
import ru.eremin.tm.endpoint.TaskEndpointImpl;
import ru.eremin.tm.endpoint.UserEndpointImpl;

import javax.xml.ws.Endpoint;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@Configuration
//@EnableWebMvc
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
        endpoint.publish("/endpoint/projectEndpoint");
        return endpoint;
    }

    @Bean
    public TaskEndpointImpl taskEndpointImpl() {
        return new TaskEndpointImpl();
    }

    @Bean
    public Endpoint taskEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), taskEndpointImpl());
        endpoint.publish("/endpoint/taskEndpoint");
        return endpoint;
    }

    @Bean
    public UserEndpointImpl userEndpointImpl() {
        return new UserEndpointImpl();
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userEndpointImpl());
        endpoint.publish("/endpoint/userEndpoint");
        return endpoint;
    }

    @Bean
    public AuthEndpointImpl authEndpointImpl() {
        return new AuthEndpointImpl();
    }

    @Bean
    public Endpoint authEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), authEndpointImpl());
        endpoint.publish("/endpoint/authEndpoint");
        return endpoint;
    }

}
