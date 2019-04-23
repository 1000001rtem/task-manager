package ru.eremin.tm.server.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.entity.AbstractEntity;
import ru.eremin.tm.server.model.entity.BaseEntity;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.model.repository.api.ITaskRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class TaskRepository implements ITaskRepository {

    @NotNull
    private final Map<String, Task> tasks;

    public TaskRepository() {
        this.tasks = new HashMap<>();
    }

    @Override
    public List<Task> findByProjectId(final String projectId) {
        @NotNull final List<Task> taskByProject = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getProjectId().equals(projectId)) taskByProject.add(task);
        }
        return taskByProject;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String id) {
        return tasks.get(id);
    }

    @NotNull
    @Override
    public List<Task> findByUserId(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        return tasksByUser;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByCreateDate(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        tasksByUser.sort(Comparator.comparing(AbstractEntity::getCreateDate));
        return tasksByUser;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByStartDate(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        tasksByUser.sort(Comparator.comparing(BaseEntity::getStartDate));
        return tasksByUser;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByEndDate(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        tasksByUser.sort(Comparator.comparing(BaseEntity::getEndDate));
        return tasksByUser;
    }

    @NotNull
    @Override
    public List<Task> findAllSortedByStatus(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        tasksByUser.sort(Comparator.comparing(BaseEntity::getStatus));
        return tasksByUser;

    }

    @NotNull
    @Override
    public List<Task> findByName(@NotNull final String userId, @NotNull final String name) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        return tasksByUser.stream().filter(e -> e.getName().contains(name)).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<Task> findByDescription(@NotNull final String userId, @NotNull final String description) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        return tasksByUser.stream().filter(e -> e.getName().contains(description)).collect(Collectors.toList());

    }

    @Override
    public void persist(@NotNull final Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void merge(@NotNull final Task task) {
        tasks.put(task.getId(), task);
    }


    @Override
    public void update(@NotNull final Task task) throws IncorrectDataException {
        if (tasks.get(task.getId()) == null) throw new IncorrectDataException("Wrong id");
        tasks.put(task.getId(), task);
    }

    @Override
    public void remove(@NotNull final String id) {
        tasks.remove(id);
    }

    @Override
    public void remove(@NotNull final List<Task> tasks) {
        tasks.forEach(e -> remove(e.getId()));
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUserId = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUserId.add(task);
        }
        remove(tasksByUserId);
    }

}
