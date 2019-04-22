package ru.eremin.tm.server.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.IAdminEndpoint;
import ru.eremin.tm.server.exeption.BadRequestExeption;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.dto.*;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.model.service.api.IProjectService;
import ru.eremin.tm.server.model.service.api.ITaskService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @autor av.eremin on 19.04.2019.
 */

@WebService
public class AdminEndpoint extends AbstractEndpoint implements IAdminEndpoint {

    @NotNull
    private ITaskService taskService;

    @NotNull
    private IProjectService projectService;

    @Override
    public ResultDTO saveJSON(@Nullable final SessionDTO session) throws SessionValidateExeption {
        sessionValidate(session);
        if (!checkAdminRole(session)) return new ResultDTO(new BadRequestExeption("wrong session"));
        @NotNull final List<ProjectDTO> projects = projectService.findAll();
        @NotNull final List<TaskDTO> tasks = taskService.findAll();
        @NotNull final Domain domain = new Domain(projects, tasks);
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @NotNull final String path = "tm-server/documents/serialization/" + session.getUserId();
        @NotNull final File file = new File(path);
        file.mkdirs();
        try {
            mapper.writeValue(new File(path + "/data.json"), domain);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultDTO(e);
        }
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO loadJSON(@Nullable final SessionDTO session) throws SessionValidateExeption {
        sessionValidate(session);
        if (!checkAdminRole(session)) return new ResultDTO(new BadRequestExeption("wrong session"));
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @NotNull final String path = "tm-server/documents/serialization/" + session.getUserId();
        @Nullable final Domain domain;
        try {
            domain = mapper.readValue(new File(path + "/data.json"), Domain.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultDTO(e);
        }
        if (domain == null || domain.getProjects() == null) return new ResultDTO(false);
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        domain.getProjects().forEach(projectService::persist);
        if (domain.getTasks() == null) return new ResultDTO(false);
        domain.getTasks().forEach(taskService::persist);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO clearJSON(@Nullable final SessionDTO session) throws SessionValidateExeption {
        sessionValidate(session);
        @NotNull final String path = "tm-server/documents/serialization/" + session.getUserId();
        @NotNull final File file = new File(path + "/data.json");
        file.delete();
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init() {
        taskService = locator.getTaskService();
        projectService = locator.getProjectService();
        System.out.println("http://localhost:8080/AdminEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/AdminEndpoint", this);
    }

    @Override
    @WebMethod(exclude = true)
    public boolean checkAdminRole(@NotNull final SessionDTO session) {
        return Role.ADMIN.equals(session.getUserRole());
    }

}
