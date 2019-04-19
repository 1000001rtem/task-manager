package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.IProjectEndpoint;
import ru.eremin.tm.server.exeption.BadRequestExeption;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.entity.session.Session;
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
    public ResultDTO persistProject(@Nullable final Session session, final ProjectDTO projectDTO) {
        if (session == null || projectDTO == null) return new ResultDTO(new BadRequestExeption());
        if (!checkSession(session)) return new ResultDTO(new BadRequestExeption("wrong session"));
        projectService.persist(projectDTO);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<ProjectDTO> findAllProjects(@Nullable final Session session) {
        if (session == null || !checkSession(session)) return null;
        return projectService.findAll(session.getUserId());
    }

    @Override
    @WebMethod
    public ProjectDTO findOneProject(@Nullable final Session session, @Nullable final String id) {
        if (session == null || id == null || id.isEmpty()) return null;
        if (!checkSession(session)) return null;
        return projectService.findOne(id);
    }

    @Override
    @WebMethod
    public ResultDTO removeProject(@Nullable final Session session, @Nullable final String id) throws IncorrectDataException {
        if (session == null || id == null || id.isEmpty()) return null;
        if (!checkSession(session)) return null;
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

    @Override
    @WebMethod(exclude = true)
    public boolean checkSession(@NotNull final Session session) {
        return session.getSign().equals(locator.getSession().getSign());
    }

}
