package ru.eremin.tm.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.model.repository.api.ITaskRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class TaskRepository implements ITaskRepository {

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

    @Override
    @Nullable
    public Task findOne(@NotNull final String id) {
        return tasks.get(id);
    }

    @Override
    @NotNull
    public List<Task> findByUserId(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUser = new ArrayList<>();
        for (final Task task : tasks.values()) {
            if (task.getUserId().equals(userId)) tasksByUser.add(task);
        }
        return tasksByUser;
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
    public void update(@NotNull final Task task) {
        if (tasks.get(task.getId()) == null) return;
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
    public void removeAll() {
        tasks.clear();
    }

}
