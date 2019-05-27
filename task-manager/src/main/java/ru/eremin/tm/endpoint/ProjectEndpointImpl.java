package ru.eremin.tm.endpoint;

import javax.jws.WebService;

/**
 * @autor av.eremin on 27.05.2019.
 */
@WebService(endpointInterface = "ru.eremin.tm.endpoint.ProjectEndpoint")
public class ProjectEndpointImpl implements ProjectEndpoint {
    @Override
    public String ping(final int a) {
        return "test " + a;
    }
}
