package ru.eremin.tm.client.util;

import ru.eremin.tm.server.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * @autor av.eremin on 06.05.2019.
 */

@ApplicationScoped
public class EndpointProducer {

    @Produces
    public UserEndpoint getUserEndpoint(){
        final UserEndpointService userEndpointService = new UserEndpointService();
        return userEndpointService.getUserEndpointPort();
    }

    @Produces
    public SessionEndpoint getSessionEndpoint(){
        final SessionEndpointService sessionEndpointService = new SessionEndpointService();
        return sessionEndpointService.getSessionEndpointPort();
    }

    @Produces
    public TaskEndpoint getTaskEndpoint(){
        final TaskEndpointService taskEndpointService = new TaskEndpointService();
        return taskEndpointService.getTaskEndpointPort();
    }

    @Produces
    public ProjectEndpoint getProjectEndpoint(){
        final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        return projectEndpointService.getProjectEndpointPort();
    }

    @Produces
    public AuthorizationEndpoint getAuthorizationEndpoint(){
        final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        return authorizationEndpointService.getAuthorizationEndpointPort();
    }

    @Produces
    public AdminEndpoint getAdminEndpoint(){
        final AdminEndpointService adminEndpointService = new AdminEndpointService();
        return adminEndpointService.getAdminEndpointPort();
    }

}
