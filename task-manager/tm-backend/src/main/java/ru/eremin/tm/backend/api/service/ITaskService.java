package ru.eremin.tm.backend.api.service;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.TaskDTO;
import ru.eremin.tm.backend.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskService extends IBasedService<Task, TaskDTO> {

    List<TaskDTO> findByProjectId(@Nullable String projectId) throws IncorrectDataException;

    void removeAllTasksInProject(@Nullable String projectId) throws IncorrectDataException;

}
