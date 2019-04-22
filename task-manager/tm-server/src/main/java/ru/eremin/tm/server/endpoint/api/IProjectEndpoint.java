package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

import java.util.List;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IProjectEndpoint {

    ResultDTO persistProject(@Nullable SessionDTO session, @Nullable ProjectDTO projectDTO) throws SessionValidateExeption;

    List<ProjectDTO> findAllProjects(@Nullable SessionDTO session) throws SessionValidateExeption;

    ProjectDTO findOneProject(@Nullable SessionDTO session, @Nullable String id) throws SessionValidateExeption;

    ResultDTO removeProject(@Nullable SessionDTO session, @Nullable String id) throws IncorrectDataException, SessionValidateExeption;

}