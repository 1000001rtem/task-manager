package ru.eremin.tm.model.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.model.repository.TaskRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public enum TaskService implements ITaskService {

    INSTANCE;

    private final TaskRepository taskRepository;

    TaskService() {
        this.taskRepository = TaskRepository.INSTANCE;
    }

    @Override
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllTasksInProject(@Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty()) return;
        List<TaskDTO> tasksInProject = findByProjectId(projectId);
        if (tasksInProject.isEmpty()) return;
        taskRepository.delete(tasksInProject.stream().map(this::getEntity).collect(Collectors.toList()));
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Nullable
    public TaskDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final Task task = taskRepository.findById(id);
        if (task == null) return null;
        return new TaskDTO(task);
    }

    @Override
    public void insert(@Nullable final TaskDTO taskDTO) {
        if (taskDTO == null) return;
        final Task task = getEntity(taskDTO);
        taskRepository.insert(task);
    }

    @Override
    public void update(@Nullable final TaskDTO taskDTO) {
        if (taskDTO == null) return;
        if (taskRepository.findById(taskDTO.getId()) == null) return;
        final Task task = getEntity(taskDTO);
        taskRepository.update(task);
    }

    @Override
    public boolean delete(@Nullable final String id) {
        if (id == null || id.isEmpty() || !isExist(id)) return false;
        taskRepository.delete(id);
        return true;
    }


    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return taskRepository.findById(id) != null;
    }

    @Override
    @NotNull
    public Task getEntity(@NotNull final TaskDTO taskDTO) {
        final Task task = new Task();
        task.setId(taskDTO.getId());
        if (taskDTO.getName() != null && !taskDTO.getName().isEmpty()) task.setName(taskDTO.getName());
        if (taskDTO.getDescription() != null && !taskDTO.getDescription().isEmpty()) {
            task.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getStartDate() != null) task.setStartDate(taskDTO.getStartDate());
        if (taskDTO.getEndDate() != null) task.setEndDate(taskDTO.getEndDate());
        if (taskDTO.getProjectId() != null && !taskDTO.getProjectId().isEmpty()) {
            task.setProjectId(taskDTO.getProjectId());
        }
        return task;
    }

}
