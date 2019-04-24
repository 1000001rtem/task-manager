package ru.eremin.tm.server.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.entity.AbstractEntity;
import ru.eremin.tm.server.model.entity.BaseEntity;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.repository.api.IProjectRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class ProjectRepository implements IProjectRepository {

    @NotNull
    private final Map<String, Project> projects;

    public ProjectRepository() {
        this.projects = new HashMap<>();
    }

    @NotNull
    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String id) {
        return projects.get(id);
    }

    @NotNull
    @Override
    public List<Project> findByUserId(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUser = getProjectsByUser(userId);
        return projectsByUser;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByCreateDate(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUser = getProjectsByUser(userId);
        projectsByUser.sort(Comparator.comparing(AbstractEntity::getCreateDate));
        return projectsByUser;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByStartDate(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUser = getProjectsByUser(userId);
        projectsByUser.sort(Comparator.comparing(BaseEntity::getStartDate));
        return projectsByUser;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByEndDate(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUser = getProjectsByUser(userId);
        projectsByUser.sort(Comparator.comparing(BaseEntity::getEndDate));
        return projectsByUser;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByStatus(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUser = getProjectsByUser(userId);
        projectsByUser.sort(Comparator.comparing(BaseEntity::getStatus));
        return projectsByUser;
    }

    @NotNull
    @Override
    public List<Project> findByName(@NotNull final String userId, @NotNull final String name) {
        @NotNull final List<Project> projectsByUser = getProjectsByUser(userId);
        projectsByUser.sort(Comparator.comparing(BaseEntity::getStartDate));
        return projectsByUser.stream().filter(e -> e.getName().contains(name)).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<Project> findByDescription(@NotNull final String userId, @NotNull final String description) {
        @NotNull final List<Project> projectsByUser = getProjectsByUser(userId);
        projectsByUser.sort(Comparator.comparing(BaseEntity::getStartDate));
        return projectsByUser.stream().filter(e -> e.getDescription().contains(description)).collect(Collectors.toList());
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
    public void update(@NotNull final Project project) throws IncorrectDataException {
        if (projects.get(project.getId()) == null) throw new IncorrectDataException("Wrong id");
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
    public void removeAll(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUserId = getProjectsByUser(userId);
        remove(projectsByUserId);
    }

    @NotNull
    private List<Project> getProjectsByUser(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUser = new ArrayList<>();
        for (final Project project : projects.values()) {
            if (project.getUserId().equals(userId)) projectsByUser.add(project);
        }
        return projectsByUser;
    }

}
