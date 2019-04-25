package ru.eremin.tm.server.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskRepository extends IBasedRepository<Task> {

    List<Task> findByProjectId(@NotNull String projectId);

    void removeAllTasksInProject(@NotNull String projectId);

}
