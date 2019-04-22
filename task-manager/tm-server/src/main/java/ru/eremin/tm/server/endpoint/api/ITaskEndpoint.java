package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;

import java.util.List;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface ITaskEndpoint {

    ResultDTO persistTask(@Nullable SessionDTO session, @Nullable TaskDTO taskDTO) throws SessionValidateExeption;

    List<TaskDTO> findAllTasks(@Nullable SessionDTO session) throws SessionValidateExeption;

    TaskDTO findOneTask(@Nullable SessionDTO session, @Nullable String id) throws SessionValidateExeption;

    ResultDTO removeTask(@Nullable SessionDTO session, @Nullable String id) throws IncorrectDataException, SessionValidateExeption;

}
