package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IProjectRepository;
import ru.eremin.tm.server.api.ITaskRepository;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.api.IUserRepository;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    @Nullable
    private EntityManagerFactory entityManagerFactory;

    @NotNull
    @Override
    public List<TaskDTO> findAll() {
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAll()
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final Project project = getProject(projectId, em);
            if (project == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByProjectId(project)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void removeAllTasksInProject(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final Project project = getProject(projectId, em);
            if (project == null) {
                em.close();
                return;
            }
            taskRepository.removeAllTasksInProject(project);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Nullable
    @Override
    public TaskDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final Task task = taskRepository.findOne(id);
            if (task == null) throw new IncorrectDataException("Wrong id");
            em.getTransaction().commit();
            return new TaskDTO(task);
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @NotNull
    @Override
    public List<TaskDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByUserId(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void persist(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Task task = getEntity(taskDTO, em);
            taskRepository.persist(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Task task = getEntity(taskDTO, em);
            taskRepository.update(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Task task = getEntity(taskDTO, em);
            taskRepository.merge(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            taskRepository.remove(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return;
            }
            taskRepository.removeAll(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Task task = taskRepository.findOne(id);
            em.getTransaction().commit();
            return task != null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByCreateDate(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStartDate(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByEndDate(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStatus(user)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByName(user, name)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = getUser(userId, em);
            if (user == null) {
                em.close();
                return Collections.emptyList();
            }
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByDescription(user, description)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return taskDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public Task getEntity(@NotNull final TaskDTO taskDTO, @NotNull final EntityManager em) {
        @Nullable final Project project = getProject(taskDTO.getProjectId(), em);
        @Nullable final User user = getUser(taskDTO.getUserId(), em);
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
    private Project getProject(@NotNull final String projectId, @NotNull final EntityManager em) {
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        return projectRepository.findOne(projectId);
    }

    @Nullable
    private User getUser(@NotNull final String userId, @NotNull final EntityManager em) {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        return userRepository.findOne(userId);
    }

}