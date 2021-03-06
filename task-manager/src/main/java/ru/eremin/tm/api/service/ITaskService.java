package ru.eremin.tm.api.service;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskService extends IBasedService<Task, TaskDTO> {

    List<TaskDTO> findByProjectId(@Nullable String projectId) throws IncorrectDataException;

    void removeAllTasksInProject(@Nullable String projectId) throws IncorrectDataException;

}
