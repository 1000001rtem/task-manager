package ru.eremin.tm.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.eremin.tm.endpoint.ProjectEndpointImpl;

import javax.xml.ws.Endpoint;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@Configuration
@Import(DataSourceConfiguration.class)
@EnableJpaRepositories("ru.eremin.tm.repository")
public class AppConfiguration {

    @Autowired
    @Qualifier(value = Bus.DEFAULT_BUS_ID)
    private Bus bus;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ProjectEndpointImpl());
        endpoint.publish("http://localhost:8080/services/baeldung");
        return endpoint;
    }

}
