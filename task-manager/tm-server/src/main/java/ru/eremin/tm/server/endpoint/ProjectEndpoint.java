package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.IProjectEndpoint;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.service.api.IProjectService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class ProjectEndpoint extends AbstractEndpoint implements IProjectEndpoint {

    @NotNull
    private IProjectService projectService;

    @Override
    @WebMethod
    public ResultDTO persistProject(@Nullable final SessionDTO sessionDTO, final ProjectDTO projectDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        @NotNull final ProjectDTO newProject = new ProjectDTO(projectDTO);
        newProject.setUserId(sessionDTO.getUserId());
        projectService.persist(newProject);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<ProjectDTO> findAllProjects(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return projectService.findByUserId(sessionDTO.getUserId());
    }

    @Override
    @WebMethod
    public ProjectDTO findOneProject(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        if (id == null || id.isEmpty()) return null;
        return projectService.findOne(id);
    }

    @Override
    @WebMethod
    public ResultDTO updateProject(@Nullable final SessionDTO sessionDTO, @Nullable final ProjectDTO projectDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        projectService.update(projectDTO);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public ResultDTO removeProject(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        projectService.remove(id);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public ResultDTO removeAllProjects(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        projectService.removeAll(sessionDTO.getUserId());
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init() {
        projectService = locator.getProjectService();
        System.out.println("http://localhost:8080/ProjectEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/ProjectEndpoint", this);
    }

}
