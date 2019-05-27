package ru.eremin.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ResultDTO;
import ru.eremin.tm.model.dto.TaskDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */

@WebService(endpointInterface = "ru.eremin.tm.endpoint.TaskEndpoint")
public class TaskEndpointImpl implements TaskEndpoint {

    @Autowired
    private ITaskService taskService;

    @Override
    public List<TaskDTO> findAllTasks(final @Nullable String userId) throws AccessForbiddenException {
        return taskService.findByUserId(userId);
    }

    @Override
    public TaskDTO findOneTask(final @Nullable String taskId) throws IncorrectDataException {
        return taskService.findOne(taskId);
    }

    @Override
    public ResultDTO createTask(final @Nullable String userId, final @Nullable TaskDTO taskDTO) throws IncorrectDataException {
        @NotNull final TaskDTO taskDTO1 = new TaskDTO(taskDTO);
        taskDTO1.setUserId(userId);
        taskService.persist(taskDTO1);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO updateTask(final @Nullable TaskDTO taskDTO) throws IncorrectDataException {
        taskService.update(taskDTO);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO removeProject(final @Nullable String taskId) throws IncorrectDataException {
        taskService.remove(taskId);
        return new ResultDTO(true);
    }

    @Override
    public List<TaskDTO> findTasksByProjectId(final @Nullable String projectId) throws IncorrectDataException {
        return taskService.findByProjectId(projectId);
    }

}
