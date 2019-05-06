package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.ITaskEndpoint;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.bootstrap.ServiceLocator;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;

import javax.inject.Inject;
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
    public ResultDTO persistTask(@Nullable final SessionDTO sessionDTO, @Nullable final TaskDTO taskDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        @NotNull final TaskDTO newTask = new TaskDTO(taskDTO);
        if (projectService.findOne(newTask.getProjectId()) == null) return new ResultDTO(false, "Wrong project Id");
        newTask.setUserId(sessionDTO.getUserId());
        taskService.persist(newTask);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<TaskDTO> findAllTasks(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findByUserId(sessionDTO.getUserId());
    }

    @Override
    public List<TaskDTO> findAllTasksSortedByCreateDate(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findAllSortedByCreateDate(sessionDTO.getUserId());
    }

    @Override
    public List<TaskDTO> findAllTasksSortedByStartDate(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findAllSortedByStartDate(sessionDTO.getUserId());
    }

    @Override
    public List<TaskDTO> findAllTasksSortedByEndDate(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findAllSortedByEndDate(sessionDTO.getUserId());
    }

    @Override
    public List<TaskDTO> findAllTasksSortedByStatus(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findAllSortedByStatus(sessionDTO.getUserId());
    }

    @Override
    public List<TaskDTO> findTasksByName(@Nullable final SessionDTO sessionDTO, @NotNull final String name) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findByName(sessionDTO.getUserId(), name);
    }

    @Override
    public List<TaskDTO> findTasksByDescription(@Nullable final SessionDTO sessionDTO, @NotNull final String description) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findByName(sessionDTO.getUserId(), description);
    }

    @Override
    @WebMethod
    public TaskDTO findOneTask(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findOne(id);
    }

    @Override
    @WebMethod
    public List<TaskDTO> findTaskByProjectId(@Nullable final SessionDTO sessionDTO, @Nullable final String projectId) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return taskService.findByProjectId(projectId);
    }

    @Override
    @WebMethod
    public ResultDTO updateTask(@Nullable final SessionDTO sessionDTO, @Nullable final TaskDTO taskDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        taskService.update(taskDTO);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public ResultDTO removeTask(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws IncorrectDataException, AccessForbiddenException {
        sessionValidate(sessionDTO);
        taskService.remove(id);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public ResultDTO removeAllTasks(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        taskService.removeAll(sessionDTO.getUserId());
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
