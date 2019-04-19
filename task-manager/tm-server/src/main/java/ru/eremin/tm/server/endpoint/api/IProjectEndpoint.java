package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.entity.session.Session;

import java.util.List;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IProjectEndpoint {

    ResultDTO persistProject(@Nullable Session session, @Nullable ProjectDTO projectDTO);

    List<ProjectDTO> findAllProjects(@Nullable Session session);

    ProjectDTO findOneProject(@Nullable Session session, @Nullable String id);

    ResultDTO removeProject(@Nullable Session session, @Nullable String id) throws IncorrectDataException;

    boolean checkSession(@NotNull Session session);
}
