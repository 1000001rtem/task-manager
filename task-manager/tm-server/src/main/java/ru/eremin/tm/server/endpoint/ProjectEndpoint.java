package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.IProjectEndpoint;
import ru.eremin.tm.server.exeption.BadRequestExeption;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
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
    public ResultDTO persistProject(@Nullable final SessionDTO session, final ProjectDTO projectDTO) throws SessionValidateExeption {
        sessionValidate(session);
        if (projectDTO == null) return new ResultDTO(new BadRequestExeption());
        @NotNull final ProjectDTO newProject = new ProjectDTO(projectDTO);
        newProject.setUserId(session.getUserId());
        projectService.persist(newProject);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<ProjectDTO> findAllProjects(@Nullable final SessionDTO session) throws SessionValidateExeption {
        sessionValidate(session);
        return projectService.findByUserId(session.getUserId());
    }

    @Override
    @WebMethod
    public ProjectDTO findOneProject(@Nullable final SessionDTO session, @Nullable final String id) throws SessionValidateExeption {
        sessionValidate(session);
        if (id == null || id.isEmpty()) return null;
        return projectService.findOne(id);
    }

    @Override
    @WebMethod
    public ResultDTO removeProject(@Nullable final SessionDTO session, @Nullable final String id) throws SessionValidateExeption, IncorrectDataException {
        sessionValidate(session);
        if (id == null || id.isEmpty()) return null;
        projectService.remove(id);
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
