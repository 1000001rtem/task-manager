package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.ITaskRepository;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.repository.TaskRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@NoArgsConstructor
public enum TaskService implements ITaskService {

    INSTANCE;

    @NotNull
    private ITaskRepository taskRepository = TaskRepository.INSTANCE;

    @Override
    public @NotNull List<TaskDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAllTasksInProject(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final List<TaskDTO> tasksInProject = findByProjectId(projectId);
        if (tasksInProject.isEmpty()) return;
        taskRepository.remove(tasksInProject.stream().map(this::getEntity).collect(Collectors.toList()));
    }

    @NotNull
    @Override
    public TaskDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Task task = taskRepository.findOne(id);
        if (task == null) throw new IncorrectDataException("Wrong id");
        return new TaskDTO(task);
    }

    @NotNull
    @Override
    public List<TaskDTO> findByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        return taskRepository.findByUserId(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void persist(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.persist(task);
    }

    @Override
    public void merge(final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.merge(task);
    }


    @Override
    public void update(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.update(task);
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        taskRepository.remove(id);
    }


    @Override
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        taskRepository.removeAll(userId);
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return taskRepository.findOne(id) != null;
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByCreateDate(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByStartDate(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByEndDate(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByStatus(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        return taskRepository.findByName(userId, name)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        return taskRepository.findByDescription(userId, description)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
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
