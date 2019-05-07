package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.repository.ProjectRepository;
import ru.eremin.tm.server.repository.TaskRepository;
import ru.eremin.tm.server.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@ApplicationScoped
@NoArgsConstructor
public class TaskService implements ITaskService {

    @Inject
    @NotNull
    private ProjectRepository projectRepository;

    @Inject
    @NotNull
    private UserRepository userRepository;

    @Inject
    @NotNull
    private TaskRepository taskRepository;

    @NotNull
    @Override
    public List<TaskDTO> findAll() {
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAll()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        return taskDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @Nullable final Project project = getProject(projectId);
        if (project == null) return Collections.emptyList();
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByProject(project)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        return taskDTOS;
    }

    @Override
    @Transactional
    public void removeAllTasksInProject(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @Nullable final Project project = getProject(projectId);
        if (project == null) return;
        @NotNull final List<Task> tasks = taskRepository.findByProject(project);
        if(tasks.isEmpty()) return;
        tasks.forEach(taskRepository::remove);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public TaskDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Task task = taskRepository.findBy(id);
        if (task == null) throw new IncorrectDataException("Wrong id");
        return new TaskDTO(task);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return Collections.emptyList();
        @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByUser(user)
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
        return taskDTOS;
    }

    @Override
    @Transactional
    public void persist(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void update(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        if (!isExist(taskDTO.getId())) throw new IncorrectDataException("Task is not exist");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void merge(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final Task task = getEntity(taskDTO);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @Nullable final Task task = taskRepository.findBy(id);
        if (task == null) throw new IncorrectDataException("Wrong id");
        taskRepository.remove(task);
    }

    @Override
    @Transactional
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @Nullable final User user = getUser(userId);
        if (user == null) return;
        @NotNull final List<Task> tasks = taskRepository.findByUser(user);
        if(tasks.isEmpty()) return;
        tasks.forEach(taskRepository::remove);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final Task task = taskRepository.findBy(id);
        return task != null;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
            @Nullable final User user = getUser(userId);
            if (user == null) return Collections.emptyList();
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByCreateDate(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            return taskDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
            @Nullable final User user = getUser(userId);
            if (user == null) return Collections.emptyList();
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStartDate(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            return taskDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
            @Nullable final User user = getUser(userId);
            if (user == null) return Collections.emptyList();
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByEndDate(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            return taskDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
            @Nullable final User user = getUser(userId);
            if (user == null) return Collections.emptyList();
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStatus(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            return taskDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
            @Nullable final User user = getUser(userId);
            if (user == null) return Collections.emptyList();
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByName(user, name)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            return taskDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
            @Nullable final User user = getUser(userId);
            if (user == null) return Collections.emptyList();
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByDescription(user, description)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            return taskDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public Task getEntity(@NotNull final TaskDTO taskDTO) {
        @Nullable final Project project = getProject(taskDTO.getProjectId());
        @Nullable final User user = getUser(taskDTO.getUserId());
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

    @Nullable
    @Transactional(readOnly = true)
    private Project getProject(@NotNull final String projectId) {
        return projectRepository.findBy(projectId);
    }

    @Nullable
    @Transactional(readOnly = true)
    private User getUser(@NotNull final String userId) {
        return userRepository.findBy(userId);
    }

}
