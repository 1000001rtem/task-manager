package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.eremin.tm.api.service.IProjectService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.ResultDTO;

import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */

@RestController
@RequestMapping(value = "/api/project")
public class ProjectRestController {

    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProjectDTO> findAllProjects(@RequestParam(value = "userId") @Nullable final String userId) throws AccessForbiddenException {
        return projectService.findByUserId(userId);
    }

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProjectDTO findOneProject(@RequestParam(value = "projectId") @Nullable final String projectId) throws IncorrectDataException {
        return projectService.findOne(projectId);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO createProject(@RequestBody @Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        @NotNull final ProjectDTO projectDTO1 = new ProjectDTO(projectDTO);
        projectDTO1.setUserId(projectDTO.getUserId());
        projectService.persist(projectDTO1);
        return new ResultDTO(true);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO updateProject(@RequestBody @Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        projectService.update(projectDTO);
        return new ResultDTO(true);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO deleteProject(@RequestParam(value = "projectId") @Nullable final String projectId) throws IncorrectDataException {
        projectService.remove(projectId);
        return new ResultDTO(true);
    }

}
