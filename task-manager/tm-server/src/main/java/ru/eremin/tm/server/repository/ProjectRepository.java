package ru.eremin.tm.server.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IProjectRepository;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class ProjectRepository implements IProjectRepository {

    @NotNull
    private final EntityManager em;

    public ProjectRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    @NotNull
    @Override
    public List<Project> findAll() {
        @NotNull final List<Project> projects = em.createQuery("SELECT e FROM Project e", Project.class).getResultList();
        return projects;
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String id) {
        return em.find(Project.class, id);
    }

    @NotNull
    @Override
    public List<Project> findByUserId(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .getResultList();
        return projects;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByCreateDate(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user ORDER BY e.createDate";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .getResultList();
        return projects;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByStartDate(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user ORDER BY e.startDate";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .getResultList();
        return projects;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByEndDate(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user ORDER BY e.endDate";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .getResultList();
        return projects;
    }

    @NotNull
    @Override
    public List<Project> findAllSortedByStatus(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user ORDER BY e.status";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .getResultList();
        return projects;
    }

    @NotNull
    @Override
    public List<Project> findByName(@NotNull final User user, @NotNull final String name) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user and e.name LIKE :name";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .setParameter("name", name)
                .getResultList();
        return projects;
    }

    @NotNull
    @Override
    public List<Project> findByDescription(@NotNull final User user, @NotNull final String description) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user and e.description LIKE :description";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .setParameter("name", description)
                .getResultList();
        return projects;
    }

    @Override
    public void persist(@NotNull final Project project) {
        em.persist(project);
    }

    @Override
    public void merge(@NotNull final Project project) {
        em.merge(project);
    }

    @Override
    public void update(@NotNull final Project project) {
        @Nullable final Project project1 = em.find(Project.class, project.getId());
        if (project1 == null) return;
        else em.merge(project);
    }

    @Override
    public void remove(@NotNull final String id) {
        @Nullable final Project project = em.find(Project.class, id);
        if (project == null) return;
        em.remove(project);
    }

    @Override
    public void removeAll(@NotNull final User user) {
        @NotNull final String query = "SELECT e FROM Project e WHERE e.user = :user";
        @NotNull final List<Project> projects = em.createQuery(query, Project.class)
                .setParameter("user", user)
                .getResultList();
        projects.forEach(em::remove);
    }

}
