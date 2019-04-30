package ru.eremin.tm.server.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskService extends IBasedService<Task, TaskDTO> {

    List<TaskDTO> findByProjectId(@Nullable ProjectDTO project) throws IncorrectDataException;

    void removeAllTasksInProject(@Nullable ProjectDTO project) throws IncorrectDataException;

}
