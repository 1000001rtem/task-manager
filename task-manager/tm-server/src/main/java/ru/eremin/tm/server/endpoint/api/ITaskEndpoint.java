package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.entity.session.Session;

import java.util.List;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface ITaskEndpoint {

    ResultDTO persistTask(@Nullable Session session, @Nullable TaskDTO taskDTO);

    List<TaskDTO> findAllTasks(@Nullable Session session);

    TaskDTO findOneTask(@Nullable Session session, @Nullable String id);

    ResultDTO removeTask(@Nullable Session session, @Nullable String id) throws IncorrectDataException;

    boolean checkSession(@NotNull Session session);

}
