package ru.eremin.tm.server.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ITaskRepository;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.repository.TaskRepository;
import ru.eremin.tm.server.utils.DBConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class TaskService implements ITaskService {


    public TaskService() {
    }

    @Override
    @SneakyThrows(SQLException.class)
    public @NotNull List<TaskDTO> findAll() {
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAll()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByProjectId(projectId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void removeAllTasksInProject(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        taskRepository.removeAllTasksInProject(projectId);
        connection.commit();
        connection.close();
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public TaskDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @Nullable final Task task = taskRepository.findOne(id);
        if (task == null) throw new IncorrectDataException("Wrong id");
        connection.commit();
        connection.close();
        return new TaskDTO(task);
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByUserId(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void persist(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.persist(task);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void merge(final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.merge(task);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void update(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.update(task);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        taskRepository.remove(id);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        taskRepository.removeAll(userId);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Task task = taskRepository.findOne(id);
        connection.commit();
        connection.close();
        return task != null;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByCreateDate(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStartDate(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByEndDate(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStatus(userId)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByName(userId, name)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<TaskDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByDescription(userId, description)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return taskDTOS;
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
