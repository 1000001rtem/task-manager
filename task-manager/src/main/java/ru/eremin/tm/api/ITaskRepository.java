package ru.eremin.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskRepository extends IBasedRepository<Task> {

    List<Task> findByProjectId(@NotNull Project project);

    void removeAllTasksInProject(@NotNull Project project);

}
