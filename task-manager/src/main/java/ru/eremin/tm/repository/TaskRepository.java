package ru.eremin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.eremin.tm.api.ITaskRepository;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Repository(TaskRepository.NAME)
public class TaskRepository implements ITaskRepository {

    public static final String NAME = "taskRepository";

    @NotNull
    @PersistenceContext
    private EntityManager em;

    @NotNull
    @Override
    public List<Task> findByProjectId(@NotNull final Project project) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.project = :project";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("project", project)
                .getResultList();
        return tasks;
    }

    @NotNull
    @Override
    public List<Task> findAll() {
        @NotNull final String query = "SELECT e FROM Task e";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class).getResultList();
        return tasks;
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String id) {
        return em.find(Task.class, id);
    }

    @NotNull
    @Override
    public List<Task> findByUserId(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .getResultList();
        return tasks;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByCreateDate(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user ORDER BY e.createDate";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .getResultList();
        return tasks;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByStartDate(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user ORDER BY e.startDate";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .getResultList();
        return tasks;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByEndDate(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user ORDER BY e.endDate";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .getResultList();
        return tasks;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByStatus(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user ORDER BY e.status";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .getResultList();
        return tasks;
    }

    @NotNull
    @Override
    public List<Task> findByName(@NotNull final User user, @NotNull final String name) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user and e.name LIKE :name";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        return tasks;
    }

    @NotNull
    @Override
    public List<Task> findByDescription(@NotNull final User user, @NotNull final String description) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user and e.description LIKE :description";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .setParameter("description", "%" + description + "%")
                .getResultList();
        return tasks;
    }

    @Override
    public void persist(@NotNull final Task task) {
        em.persist(task);
    }

    @Override
    public void merge(@NotNull final Task task) {
        em.merge(task);
    }

    @Override
    public void update(@NotNull final Task task) {
        @Nullable final Task task1 = em.find(Task.class, task.getId());
        if (task1 != null) em.merge(task);
    }

    @Override
    public void remove(@NotNull final String id) {
        @Nullable final Task task = em.find(Task.class, id);
        if (task == null) return;
        em.remove(task);
    }

    @Override
    public void removeAllTasksInProject(@Nullable final Project project) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.project = :project";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("project", project)
                .getResultList();
        tasks.forEach(em::remove);
    }

    @Override
    public void removeAll(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Task e WHERE e.user = :user";
        @NotNull final List<Task> tasks = em.createQuery(query, Task.class)
                .setParameter("user", user)
                .getResultList();
        tasks.forEach(em::remove);
    }

}
