package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.tm.api.IProjectRepository;
import ru.eremin.tm.api.ITaskRepository;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.api.IUserRepository;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.model.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@NoArgsConstructor
@Service(TaskService.NAME)
public class TaskService implements ITaskService {

    public static final String NAME = "taskService";

    @NotNull
    @Autowired
    private ITaskRepository taskRepository;

    @NotNull
    @Autowired
    private IProjectRepository projectRepository;

    @NotNull
    @Autowired
    private IUserRepository userRepository;

    @Override
    public @NotNull List<TaskDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @Nullable final Project project = projectRepository.findOne(projectId);
        if (project == null) throw new IncorrectDataException("Wrong project id");
        return taskRepository.findByProjectId(project)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeAllTasksInProject(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final List<TaskDTO> tasksInProject = findByProjectId(projectId);
        if (tasksInProject.isEmpty()) return;
        tasksInProject.forEach(e -> taskRepository.remove(e.getId()));
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public TaskDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Task task = taskRepository.findOne(id);
        if (task == null) throw new IncorrectDataException("Wrong id");
        return new TaskDTO(task);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return Collections.emptyList();
        return taskRepository.findByUserId(user)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void persist(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.persist(task);
    }

    @Override
    @Transactional
    public void merge(final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.merge(task);
    }


    @Override
    @Transactional
    public void update(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.update(task);
    }

    @Override
    @Transactional
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        taskRepository.remove(id);
    }


    @Override
    @Transactional
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        taskRepository.removeAll(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return taskRepository.findOne(id) != null;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByCreateDate(user)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByStartDate(user)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByEndDate(user)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return taskRepository.findAllSortedByStatus(user)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return taskRepository.findByName(user, name)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) throw new AccessForbiddenException();
        return taskRepository.findByDescription(user, description)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public Task getEntity(@NotNull final TaskDTO taskDTO) {
        @Nullable final Project project = projectRepository.findOne(taskDTO.getProjectId());
        @Nullable final User user = userRepository.findOne(taskDTO.getUserId());
        @NotNull final Task task = new Task();
        task.setId(taskDTO.getId());
        if (taskDTO.getName() != null && !taskDTO.getName().isEmpty()) task.setName(taskDTO.getName());
        if (taskDTO.getDescription() != null && !taskDTO.getDescription().isEmpty()) {
            task.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getStartDate() != null) task.setStartDate(taskDTO.getStartDate());
        if (taskDTO.getEndDate() != null) task.setEndDate(taskDTO.getEndDate());
        if (project != null) task.setProject(project);
        if (user != null) task.setUser(user);
        task.setStatus(taskDTO.getStatus());
        task.setCreateDate(taskDTO.getCreateDate());
        return task;
    }

}
