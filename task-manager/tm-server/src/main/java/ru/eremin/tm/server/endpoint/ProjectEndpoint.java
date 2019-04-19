package ru.eremin.tm.server.endpoint;

import ru.eremin.tm.server.model.dto.ProjectDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class ProjectEndpoint {

    @WebMethod
    public void add(final ProjectDTO project) {

    }
}
