package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.eremin.tm.api.service.ITaskService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.web.ResultDTO;

import java.util.List;


/**
 * @autor av.eremin on 28.05.2019.
 */

@RestController
@RequestMapping(value = "/api/task")
public class TaskRestController {

    @Autowired
    private ITaskService taskService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> findAllTasks(@RequestParam(name = "userId") @Nullable final String userId) throws AccessForbiddenException {
        return taskService.findByUserId(userId);
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

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
