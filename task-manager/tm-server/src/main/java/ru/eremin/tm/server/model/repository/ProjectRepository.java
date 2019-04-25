package ru.eremin.tm.server.model.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.enumerated.Status;
import ru.eremin.tm.server.model.repository.api.IProjectRepository;
import ru.eremin.tm.server.utils.FieldConst;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class ProjectRepository implements IProjectRepository {

    @NotNull
    private final Connection connection;

    public ProjectRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAll() {
        @NotNull final String query = "SELECT * FROM `project_table`;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) projects.add(fetch(resultSet));
        statement.close();
        return projects;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Project findOne(@NotNull final String id) {
        @NotNull final String query = "SELECT * FROM `project_table` WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @Nullable final Project project = fetch(resultSet);
        statement.close();
        return project;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM `project_table` WHERE `user_id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) projects.add(fetch(resultSet));
        statement.close();
        return projects;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAllSortedByCreateDate(@NotNull final String userId) {
        return getSortProjects(userId, "`create_date`");

    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAllSortedByStartDate(@NotNull final String userId) {
        return getSortProjects(userId, "`start_date`");
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAllSortedByEndDate(@NotNull final String userId) {
        return getSortProjects(userId, "`end_date`");
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAllSortedByStatus(@NotNull final String userId) {
        return getSortProjects(userId, "`project_status`");
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findByName(@NotNull final String userId, @NotNull final String name) {
        @NotNull final String query = "SELECT * FROM `project_table` WHERE `user_id` = ? and `project_name` LIKE ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, name);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) projects.add(fetch(resultSet));
        statement.close();
        return projects;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findByDescription(@NotNull final String userId, @NotNull final String description) {
        @NotNull final String query = "SELECT * FROM `project_table` WHERE `user_id` = ? and `project_description` LIKE ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, description);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) projects.add(fetch(resultSet));
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public void persist(@NotNull final Project project) {
        @NotNull final String query = "INSERT INTO `project_table`" + "(" +
                FieldConst.ID + ", " +
                FieldConst.CREATE_DATE + ", " +
                FieldConst.PROJECT_NAME + ", " +
                FieldConst.PROJECT_DESC + ", " +
                FieldConst.START_DATE + ", " +
                FieldConst.END_DATE + ", " +
                FieldConst.PROJECT_STATUS + ", " +
                FieldConst.USER_ID + ") " +
                "VALUES (?,?,?,?,?,?,?,?);";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, project.getId());
        statement.setDate(2, new Date(project.getCreateDate().getTime()));
        statement.setString(3, project.getName());
        statement.setString(4, project.getDescription());
        statement.setDate(5, new Date(project.getStartDate().getTime()));
        statement.setDate(6, new Date(project.getEndDate().getTime()));
        statement.setString(7, project.getStatus().toString());
        statement.setString(8, project.getUserId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void merge(@NotNull final Project project) {
        @NotNull final Project project1 = findOne(project.getId());
        if (project1 == null) persist(project);
        else update(project);
    }

    @Override
    @SneakyThrows
    public void update(@NotNull final Project project) {
        @NotNull final String query = "UPDATE `project_table` SET " +
                FieldConst.PROJECT_NAME + "= ?, " +
                FieldConst.PROJECT_DESC + "= ?, " +
                FieldConst.START_DATE + "= ?, " +
                FieldConst.END_DATE + "= ?, " +
                FieldConst.PROJECT_STATUS + "= ?, " +
                FieldConst.USER_ID + "= ? " +
                "WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setDate(3, new Date(project.getStartDate().getTime()));
        statement.setDate(4, new Date(project.getEndDate().getTime()));
        statement.setString(5, project.getStatus().toString());
        statement.setString(6, project.getUserId());
        statement.setString(7, project.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final String id) {
        @NotNull final String query = "DELETE FROM `project_table` WHERE id = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void remove(@NotNull final List<Project> projects) {
        projects.forEach(e -> remove(e.getId()));
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        @NotNull final List<Project> projectsByUserId = findByUserId(userId);
        remove(projectsByUserId);
    }

    @Nullable
    @SneakyThrows
    private Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Project project = new Project();
        project.setId(row.getString(FieldConst.ID));
        project.setCreateDate(row.getDate(FieldConst.CREATE_DATE));
        project.setName(row.getString(FieldConst.PROJECT_NAME));
        project.setDescription(row.getString(FieldConst.PROJECT_DESC));
        project.setStartDate(row.getDate(FieldConst.START_DATE));
        project.setEndDate(row.getDate(FieldConst.END_DATE));
        project.setStatus(Status.getByDisplayName(row.getString(FieldConst.PROJECT_STATUS)));
        project.setUserId(row.getString(FieldConst.USER_ID));
        return project;
    }

    @NotNull
    private List<Project> getSortProjects(final @NotNull String userId, final @NotNull String parameter) throws SQLException {
        @NotNull final String query = "SELECT * FROM `project_table` WHERE `user_id` = ? ORDER BY " + parameter;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) projects.add(fetch(resultSet));
        statement.close();
        return projects;
    }

}
