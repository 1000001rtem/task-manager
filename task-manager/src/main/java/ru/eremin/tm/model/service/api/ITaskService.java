package ru.eremin.tm.model.service.api;

import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.Task;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface ITaskService extends IService<Task, TaskDTO> {

    List<TaskDTO> findByProjectId(String projectId);

    List<TaskDTO> findByUserId(String userId);

    void removeAllTasksInProject(String projectId);

    void removeAll(String userId);

}
