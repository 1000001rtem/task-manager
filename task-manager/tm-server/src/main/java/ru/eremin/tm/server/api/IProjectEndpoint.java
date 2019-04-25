package ru.eremin.tm.server.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

import java.util.List;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IProjectEndpoint {

    ResultDTO persistProject(@Nullable SessionDTO sessionDTO, @Nullable ProjectDTO projectDTO) throws AccessForbiddenException, IncorrectDataException;

    List<ProjectDTO> findAllProjects(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    ProjectDTO findOneProject(@Nullable SessionDTO sessionDTO, @Nullable String id) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO updateProject(@Nullable SessionDTO sessionDTO, @Nullable ProjectDTO projectDTO) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO removeProject(@Nullable SessionDTO sessionDTO, @Nullable String id) throws IncorrectDataException, AccessForbiddenException;

    ResultDTO removeAllProjects(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<ProjectDTO> findAllProjectsSortedByCreateDate(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<ProjectDTO> findAllProjectsSortedByStartDate(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<ProjectDTO> findAllProjectsSortedByEndDate(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<ProjectDTO> findAllProjectsSortedByStatus(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    List<ProjectDTO> findProjectsByName(@Nullable SessionDTO sessionDTO, @NotNull String name) throws AccessForbiddenException, IncorrectDataException;

    List<ProjectDTO> findProjectsByDescription(@Nullable SessionDTO sessionDTO, @NotNull String description) throws AccessForbiddenException, IncorrectDataException;

}
