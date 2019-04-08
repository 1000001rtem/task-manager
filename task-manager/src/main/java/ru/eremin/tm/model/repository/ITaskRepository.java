package ru.eremin.tm.model.repository;

import ru.eremin.tm.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskRepository extends IRepository<Task> {

    List<Task> findByProjectId(String projectId);

}
