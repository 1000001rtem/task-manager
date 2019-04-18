package ru.eremin.tm.server.endpoint;

import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.repository.ProjectRepository;
import ru.eremin.tm.server.model.repository.api.IProjectRepository;
import ru.eremin.tm.server.model.service.ProjectService;
import ru.eremin.tm.server.model.service.api.IProjectService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class ProjectEndpoint {

    @WebMethod
    public void add(final ProjectDTO project){

    }
}
