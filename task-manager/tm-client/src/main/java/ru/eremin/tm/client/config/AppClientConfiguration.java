package ru.eremin.tm.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.eremin.tm.server.endpoint.*;

/**
 * @autor av.eremin on 13.05.2019.
 */

@Configuration
@ComponentScan("ru.eremin.tm.client")
public class AppClientConfiguration {

    @Bean
    public UserEndpoint getUserEndpoint() {
        final UserEndpointService userEndpointService = new UserEndpointService();
        return userEndpointService.getUserEndpointPort();
    }

    @Bean
    public SessionEndpoint getSessionEndpoint() {
        final SessionEndpointService sessionEndpointService = new SessionEndpointService();
        return sessionEndpointService.getSessionEndpointPort();
    }

    @Bean
    public TaskEndpoint getTaskEndpoint() {
        final TaskEndpointService taskEndpointService = new TaskEndpointService();
        return taskEndpointService.getTaskEndpointPort();
    }

    @Bean
    public ProjectEndpoint getProjectEndpoint() {
        final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        return projectEndpointService.getProjectEndpointPort();
    }

    @Bean
    public AuthorizationEndpoint getAuthorizationEndpoint() {
        final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        return authorizationEndpointService.getAuthorizationEndpointPort();
    }

    @Bean
    public AdminEndpoint getAdminEndpoint() {
        final AdminEndpointService adminEndpointService = new AdminEndpointService();
        return adminEndpointService.getAdminEndpointPort();
    }

    @Bean
    public ServerEndpoint getServerEndpoint() {
        final ServerEndpointService serverEndpointService = new ServerEndpointService();
        return serverEndpointService.getServerEndpointPort();
    }

}
