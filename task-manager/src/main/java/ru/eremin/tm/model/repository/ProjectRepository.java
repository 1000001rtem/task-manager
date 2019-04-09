package ru.eremin.tm.model.repository;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public enum ProjectRepository implements IProjectRepository {

    INSTANCE;

    private final Map<String, Project> projects;

    ProjectRepository() {
        this.projects = new HashMap<>();
    }

    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    @Override
    @Nullable
    public Project findById(final String id) {
        return projects.get(id);
    }

    @Override
    public void insert(final Project project) {
        projects.put(project.getId(), project);
    }

    @Override
    public void update(final Project project) {
        if (projects.get(project.getId()) == null) return;
        projects.put(project.getId(), project);
    }

    @Override
    public void delete(final String id) {
        projects.remove(id);
    }

    @Override
    public void deleteAll() {
        projects.clear();
    }

}
