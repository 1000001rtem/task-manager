package ru.eremin.tm.server.model.repository.api;

import ru.eremin.tm.server.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskRepository extends IBasedRepository<Task> {

    List<Task> findByProjectId(String projectId);

}
