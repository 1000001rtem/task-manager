package ru.eremin.tm.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class ProjectRepository implements IProjectRepository {

    private final Map<String, Project> projects;

    public ProjectRepository() {
        this.projects = new HashMap<>();
    }

    @Override
    @NotNull
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    @Override
    @Nullable
    public Project findOne(@NotNull final String id) {
        return projects.get(id);
    }

    @Override
    public void persist(@NotNull final Project project) {
        projects.put(project.getId(), project);
    }

    @Override
    public void merge(@NotNull final Project project) {
        projects.put(project.getId(), project);
    }

    @Override
    public void update(@NotNull final Project project) {
        if (projects.get(project.getId()) == null) return;
        projects.put(project.getId(), project);
    }

    @Override
    public void remove(@NotNull final String id) {
        projects.remove(id);
    }

    @Override
    public void remove(@NotNull final List<Project> projects) {
        projects.forEach(e -> remove(e.getId()));
    }

    @Override
    public void removeAll() {
        projects.clear();
    }

}
