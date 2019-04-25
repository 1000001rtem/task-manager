package ru.eremin.tm.server.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;

import java.util.List;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface ITaskEndpoint {

    ResultDTO persistTask(@Nullable SessionDTO sessionDTO, @Nullable TaskDTO taskDTO) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findAllTasks(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    TaskDTO findOneTask(@Nullable SessionDTO sessionDTO, @Nullable String id) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findTaskByProjectId(@Nullable SessionDTO sessionDTO, @Nullable String projectId) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO updateTask(@Nullable SessionDTO sessionDTO, @Nullable TaskDTO taskDTO) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO removeTask(@Nullable SessionDTO sessionDTO, @Nullable String id) throws IncorrectDataException, AccessForbiddenException;

    ResultDTO removeAllTasks(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findAllTasksSortedByCreateDate(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findAllTasksSortedByStartDate(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findAllTasksSortedByEndDate(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findAllTasksSortedByStatus(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findTasksByName(@Nullable SessionDTO sessionDTO, @NotNull String name) throws AccessForbiddenException, IncorrectDataException;

    List<TaskDTO> findTasksByDescription(@Nullable SessionDTO sessionDTO, @NotNull String description) throws AccessForbiddenException, IncorrectDataException;


}
