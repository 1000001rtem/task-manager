package ru.eremin.tm.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.ResultDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */

@WebService
public interface ProjectEndpoint {

    List<ProjectDTO> findAllProjects(@Nullable final String userId) throws AccessForbiddenException;

    ProjectDTO findOneProject(@Nullable final String projectId) throws IncorrectDataException;

    ResultDTO createProject(@Nullable final String userId, @Nullable final ProjectDTO projectDTO) throws IncorrectDataException;

    ResultDTO updateProject(@Nullable final ProjectDTO projectDTO) throws IncorrectDataException;

    ResultDTO removeProject(@Nullable final String projectId) throws IncorrectDataException;

}
