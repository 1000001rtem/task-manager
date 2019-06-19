package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eremin.tm.api.service.IProjectService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.web.ResultDTO;
import ru.eremin.tm.security.JwtTokenProvider;

import java.util.List;

/**
 * @autor av.eremin on 27.05.2019.
 */
@RestController
@RequestMapping(value = "/api/project")
public class ProjectRestController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProjectDTO> findAllProjects(@RequestHeader("Authorization") @NotNull final String token) throws AccessForbiddenException {
        @Nullable final String login = tokenProvider.getUserLogin(token.substring(7));
        return projectService.findByUserLogin(login);
    }

    @GetMapping(value = "/findPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ProjectDTO> findPage(@RequestHeader("Authorization") @NotNull final String token,
                                     @RequestParam(name = "page") @Nullable final int page,
                                     @RequestParam(name = "size") @Nullable final int size) throws AccessForbiddenException {
        @Nullable final String login = tokenProvider.getUserLogin(token.substring(7));
        return projectService.findByUserLogin(login, PageRequest.of(page - 1, size));
    }

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProjectDTO findOneProject(@RequestParam(name = "projectId") @Nullable final String projectId) throws IncorrectDataException {
        return projectService.findOne(projectId);
    }

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO createProject(@RequestBody @Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        @NotNull final ProjectDTO projectDTO1 = new ProjectDTO(projectDTO);
        projectDTO1.setUserId(projectDTO.getUserId());
        projectService.persist(projectDTO1);
        return new ResultDTO(true);
    }

    @PutMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO updateProject(@RequestBody @Nullable final ProjectDTO projectDTO) throws IncorrectDataException {
        System.out.println(projectDTO);
        projectService.update(projectDTO);
        return new ResultDTO(true);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO deleteProject(@RequestParam(name = "projectId") @Nullable final String projectId) throws IncorrectDataException {
        projectService.remove(projectId);
        return new ResultDTO(true);
    }

    @DeleteMapping(value = "/deleteAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO deleteAllProjects(@RequestParam(name = "userId") @Nullable final String userId) throws AccessForbiddenException {
        projectService.removeAll(userId);
        return new ResultDTO(true);
    }

}
