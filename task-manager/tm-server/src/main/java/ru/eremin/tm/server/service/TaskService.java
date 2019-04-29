package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.config.SqlSessionConfig;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.entity.Task;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@NoArgsConstructor
public class TaskService implements ITaskService {

    @NotNull
    private final SqlSessionFactory sessionFactory = SqlSessionConfig.getSessionFactory();

    @NotNull
    @Override
    public List<TaskDTO> findAll() {
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAll()
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findByProjectId(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByProjectId(projectId)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void removeAllTasksInProject(@Nullable final String projectId) throws IncorrectDataException {
        if (projectId == null || projectId.isEmpty()) throw new IncorrectDataException("Wrong project id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.removeAllTasksInProject(projectId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Nullable
    @Override
    public TaskDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @Nullable final Task task = taskRepository.findOne(id);
            if (task == null) throw new IncorrectDataException("Wrong id");
            sqlSession.commit();
            return new TaskDTO(task);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    @NotNull
    @Override
    public List<TaskDTO> findByUserId(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByUserId(userId)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void persist(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final Task task = getEntity(taskDTO);
            taskRepository.persist(task);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(@Nullable final TaskDTO taskDTO) throws IncorrectDataException {
        if (taskDTO == null) throw new IncorrectDataException("Task is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final Task task = getEntity(taskDTO);
            taskRepository.update(task);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.remove(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            taskRepository.removeAll(userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final Task task = taskRepository.findOne(id);
            sqlSession.commit();
            return task != null;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByCreateDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByCreateDate(userId)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByStartDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStartDate(userId)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByEndDate(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByEndDate(userId)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllSortedByStatus(@Nullable final String userId) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findAllSortedByStatus(userId)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findByName(@Nullable final String userId, @Nullable final String name) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByName(userId, name)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<TaskDTO> findByDescription(@Nullable final String userId, @Nullable final String description) throws AccessForbiddenException {
        if (userId == null || userId.isEmpty() || description == null || description.isEmpty())
            throw new AccessForbiddenException();
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final TaskRepository taskRepository = sqlSession.getMapper(TaskRepository.class);
            @NotNull final List<TaskDTO> taskDTOS = taskRepository.findByDescription(userId, description)
                    .stream()
                    .map(TaskDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return taskDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
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
            task.setProject(taskDTO.getProjectId());
        }
        if (taskDTO.getUserId() != null && !taskDTO.getUserId().isEmpty()) task.setUser(taskDTO.getUserId());
        task.setStatus(taskDTO.getStatus());
        task.setCreateDate(taskDTO.getCreateDate());
        return task;
    }

}
