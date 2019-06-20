package ru.eremin.tm.backend.rest;

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
import ru.eremin.tm.backend.api.service.ITaskService;
import ru.eremin.tm.backend.exeption.AccessForbiddenException;
import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.TaskDTO;
import ru.eremin.tm.backend.model.dto.web.ResultDTO;
import ru.eremin.tm.backend.security.JwtTokenProvider;

import java.util.List;


/**
 * @autor av.eremin on 28.05.2019.
 */

@RestController
@RequestMapping(value = "/api/task")
public class TaskRestController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> findAllTasks(@RequestHeader("Authorization") @NotNull final String token) throws AccessForbiddenException {
        @Nullable final String login = tokenProvider.getUserLogin(token.substring(7));
        return taskService.findByUserLogin(login);
    }

    @GetMapping(value = "/findPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<TaskDTO> findPage(@RequestHeader("Authorization") @NotNull final String token,
                                  @RequestParam(name = "page") @Nullable final int page,
                                  @RequestParam(name = "size") @Nullable final int size) throws AccessForbiddenException {
        @Nullable final String login = tokenProvider.getUserLogin(token.substring(7));
        return taskService.findByUserLogin(login, PageRequest.of(page - 1, size));
    }

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TaskDTO findOneTask(@RequestParam(name = "taskId") @Nullable final String taskId) throws IncorrectDataException {
        return taskService.findOne(taskId);
    }

    @GetMapping(value = "/findByProject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> findTaskByProjectId(@RequestParam(name = "projectId") @Nullable final String projectId) throws IncorrectDataException {
        return taskService.findByProjectId(projectId);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO createTask(@RequestBody @Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        @NotNull final TaskDTO taskDTO1 = new TaskDTO(taskDTO);
        taskDTO1.setUserId(taskDTO.getUserId());
        taskService.persist(taskDTO1);
        return new ResultDTO(true);
    }

    @PutMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO updateTask(@RequestBody @Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        taskService.update(taskDTO);
        return new ResultDTO(true);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO deleteTask(@RequestParam(name = "taskId") @Nullable final String taskId) throws IncorrectDataException {
        taskService.remove(taskId);
        return new ResultDTO(true);
    }

    @DeleteMapping(value = "/deleteAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO deleteAllTasks(@RequestParam(name = "userId") @Nullable final String userId) throws AccessForbiddenException {
        taskService.removeAll(userId);
        return new ResultDTO(true);
    }

}
