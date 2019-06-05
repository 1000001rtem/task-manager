package ru.eremin.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.eremin.tm.api.endpoint.ProjectEndpoint;
import ru.eremin.tm.api.service.IProjectService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.web.ResultDTO;

import javax.jws.WebService;
import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */

@WebService(endpointInterface = "ru.eremin.tm.api.endpoint.ProjectEndpoint")
public class ProjectEndpointImpl implements ProjectEndpoint {

    @Autowired
    private IProjectService projectService;

    @Override
    public List<ProjectDTO> findAllProjects(final @Nullable String userId) throws AccessForbiddenException {
        return projectService.findByUserId(userId);
    }

    @Override
    public ProjectDTO findOneProject(final @Nullable String projectId) throws IncorrectDataException {
        return projectService.findOne(projectId);
    }

    @Override
    public ResultDTO createProject(final @Nullable String userId, @Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        @NotNull final ProjectDTO projectDTO1 = new ProjectDTO(projectDTO);
        projectDTO1.setUserId(userId);
        projectService.persist(projectDTO1);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO updateProject(final @Nullable ProjectDTO projectDTO) throws IncorrectDataException {
        projectService.update(projectDTO);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO removeProject(final @Nullable String projectId) throws IncorrectDataException {
        projectService.remove(projectId);
        return new ResultDTO(true);
    }

}
