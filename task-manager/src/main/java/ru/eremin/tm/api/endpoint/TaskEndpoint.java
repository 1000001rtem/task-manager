package ru.eremin.tm.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ResultDTO;
import ru.eremin.tm.model.dto.TaskDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */

@WebService
public interface TaskEndpoint {

    List<TaskDTO> findAllTasks(@Nullable final String userId) throws AccessForbiddenException;

    TaskDTO findOneTask(@Nullable final String taskId) throws IncorrectDataException;

    ResultDTO createTask(@Nullable final String userId, @Nullable final TaskDTO taskDTO) throws IncorrectDataException;

    ResultDTO updateTask(@Nullable final TaskDTO taskDTO) throws IncorrectDataException;

    ResultDTO removeProject(@Nullable final String taskId) throws IncorrectDataException;

    List<TaskDTO> findTasksByProjectId(@Nullable final String projectId) throws IncorrectDataException;

}
