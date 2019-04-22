package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.ITaskEndpoint;
import ru.eremin.tm.server.exeption.BadRequestExeption;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.service.api.IProjectService;
import ru.eremin.tm.server.model.service.api.ITaskService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class TaskEndpoint extends AbstractEndpoint implements ITaskEndpoint {

    @NotNull
    private ITaskService taskService;

    @NotNull
    private IProjectService projectService;

    @Override
    @WebMethod
    public ResultDTO persistTask(@Nullable final SessionDTO session, @Nullable final TaskDTO taskDTO) throws SessionValidateExeption {
        sessionValidate(session);
        if (taskDTO == null) new ResultDTO(new BadRequestExeption());
        @NotNull final TaskDTO newTask = new TaskDTO(taskDTO);
        if (projectService.findOne(newTask.getProjectId()) == null) return new ResultDTO(false, "Wrong project Id");
        newTask.setUserId(session.getUserId());
        taskService.persist(newTask);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<TaskDTO> findAllTasks(@Nullable final SessionDTO session) throws SessionValidateExeption {
        sessionValidate(session);
        return taskService.findByUserId(session.getUserId());
    }

    @Override
    @WebMethod
    public TaskDTO findOneTask(@Nullable final SessionDTO session, @Nullable final String id) throws SessionValidateExeption {
        sessionValidate(session);
        if (id == null || id.isEmpty()) return null;
        return taskService.findOne(id);
    }

    @Override
    @WebMethod
    public ResultDTO removeTask(@Nullable final SessionDTO session, @Nullable final String id) throws IncorrectDataException, SessionValidateExeption {
        sessionValidate(session);
        if (id == null || id.isEmpty()) new ResultDTO(new BadRequestExeption());
        taskService.remove(id);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init() {
        taskService = locator.getTaskService();
        projectService = locator.getProjectService();
        System.out.println("http://localhost:8080/TaskEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/TaskEndpoint", this);
    }

}
