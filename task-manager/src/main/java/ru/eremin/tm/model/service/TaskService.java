package ru.eremin.tm.model.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.base.AbstractDTO;
import ru.eremin.tm.model.dto.base.BaseDTO;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.model.repository.api.ITaskRepository;
import ru.eremin.tm.model.service.api.ITaskService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class TaskService implements ITaskService {

    @Nullable
    private ITaskRepository taskRepository;

    public TaskService(@Nullable final ITaskRepository taskRepository) {
        if (taskRepository == null) return;
        this.taskRepository = taskRepository;
    }

    @Override
    @NotNull
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAllTasksInProject(@Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty()) return;
        @NotNull final List<TaskDTO> tasksInProject = findByProjectId(projectId);
        if (tasksInProject.isEmpty()) return;
        taskRepository.remove(tasksInProject.stream().map(this::getEntity).collect(Collectors.toList()));
    }

    @Override
    @Nullable
    public TaskDTO findOne(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        @Nullable final Task task = taskRepository.findOne(id);
        if (task == null) return null;
        return new TaskDTO(task);
    }

    @Override
    @NotNull
    public List<TaskDTO> findAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        return taskRepository.findByUserId(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void persist(@Nullable final TaskDTO taskDTO) {
        if (taskDTO == null) return;
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.persist(task);
    }

    @Override
    public void merge(final TaskDTO taskDTO) {
        if (taskDTO == null) return;
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.merge(task);
    }


    @Override
    public void update(@Nullable final TaskDTO taskDTO) {
        if (taskDTO == null) return;
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.update(task);
    }

    @Override
    public boolean remove(@Nullable final String id) {
        if (id == null || id.isEmpty() || isExist(id)) return false;
        taskRepository.remove(id);
        return true;
    }


    @Override
    public void removeAll(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return;
        taskRepository.removeAll(userId);
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return true;
        return taskRepository.findOne(id) == null;
    }

    @Override
    public List<TaskDTO> findAllSortedByCreateDate(@Nullable final String userId) {
        @NotNull final List<TaskDTO> tasks = findAll(userId);
        tasks.sort(Comparator.comparing(AbstractDTO::getCreateDate));
        return tasks;
    }

    @Override
    public List<TaskDTO> findAllSortedByStartDate(@Nullable final String userId) {
        @NotNull final List<TaskDTO> tasks = findAll(userId);
        tasks.sort(Comparator.comparing(BaseDTO::getStartDate));
        return tasks;
    }

    @Override
    public List<TaskDTO> findAllSortedByEndDate(@Nullable final String userId) {
        @NotNull final List<TaskDTO> tasks = findAll(userId);
        tasks.sort(Comparator.comparing(BaseDTO::getEndDate));
        return tasks;
    }

    @Override
    public List<TaskDTO> findAllSortedByStatus(@Nullable final String userId) {
        @NotNull final List<TaskDTO> tasks = findAll(userId);
        tasks.sort(Comparator.comparing(BaseDTO::getStatus));
        return tasks;
    }

    @Override
    @NotNull
    public Task getEntity(@NotNull final TaskDTO taskDTO) {
        @NotNull final Task task = new Task();
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
        if (taskDTO.getUserId() != null && !taskDTO.getUserId().isEmpty()) task.setUserId(taskDTO.getUserId());
        task.setStatus(taskDTO.getStatus());
        task.setCreateDate(taskDTO.getCreateDate());
        return task;
    }

}
