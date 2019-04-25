package ru.eremin.tm.server.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ITaskRepository;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.model.entity.enumerated.Status;
import ru.eremin.tm.server.utils.FieldConst;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class TaskRepository implements ITaskRepository {

    @NotNull
    private final Connection connection;

    public TaskRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findByProjectId(@NotNull final String projectId) {
        @NotNull final String query = "SELECT * FROM `task_table` WHERE `project_id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        statement.close();
        return tasks;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findAll() {
        @NotNull final String query = "SELECT * FROM `task_table`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        statement.close();
        return tasks;
    }

    @Nullable
    @Override
    @SneakyThrows(SQLException.class)
    public Task findOne(@NotNull final String id) {
        @NotNull final String query = "SELECT * FROM `task_table` WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @Nullable final Task task = fetch(resultSet);
        statement.close();
        return task;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM `task_table` WHERE `user_id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        statement.close();
        return tasks;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findAllSortedByCreateDate(@NotNull final String userId) {
        return getSortTasks(userId, "`create_date`");
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findAllSortedByStartDate(@NotNull final String userId) {
        return getSortTasks(userId, "`start_date`");
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findAllSortedByEndDate(@NotNull final String userId) {
        return getSortTasks(userId, "`end_date`");
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findAllSortedByStatus(@NotNull final String userId) {
        return getSortTasks(userId, "`task_status`");
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findByName(@NotNull final String userId, @NotNull final String name) {
        @NotNull final String query = "SELECT * FROM `task_table` WHERE `user_id` = ? and `task_name` LIKE ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, name);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        statement.close();
        return tasks;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<Task> findByDescription(@NotNull final String userId, @NotNull final String description) {
        @NotNull final String query = "SELECT * FROM `task_table` WHERE `user_id` = ? and `task_description` LIKE ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, description);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void persist(@NotNull final Task task) {
        @NotNull final String query = "INSERT INTO `task_table`" + "(" +
                FieldConst.ID + ", " +
                FieldConst.CREATE_DATE + ", " +
                FieldConst.TASK_NAME + ", " +
                FieldConst.TASK_DESC + ", " +
                FieldConst.START_DATE + ", " +
                FieldConst.END_DATE + ", " +
                FieldConst.TASK_STATUS + ", " +
                FieldConst.USER_ID + ", " +
                FieldConst.PROJECT_ID + ") " +
                "VALUES (?,?,?,?,?,?,?,?,?);";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getId());
        statement.setDate(2, new Date(task.getCreateDate().getTime()));
        statement.setString(3, task.getName());
        statement.setString(4, task.getDescription());
        statement.setDate(5, new Date(task.getStartDate().getTime()));
        statement.setDate(6, new Date(task.getEndDate().getTime()));
        statement.setString(7, task.getStatus().toString());
        statement.setString(8, task.getUserId());
        statement.setString(9, task.getProjectId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void merge(@NotNull final Task task) {
        @NotNull final Task task1 = findOne(task.getId());
        if (task1 == null) persist(task);
        else update(task);
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void update(@NotNull final Task task) {
        @NotNull final String query = "UPDATE `task_table` SET " +
                FieldConst.TASK_NAME + "= ?, " +
                FieldConst.TASK_DESC + "= ?, " +
                FieldConst.START_DATE + "= ?, " +
                FieldConst.END_DATE + "= ?, " +
                FieldConst.TASK_STATUS + "= ?, " +
                FieldConst.USER_ID + "= ?, " +
                FieldConst.PROJECT_ID + "= ? " +
                "WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getName());
        statement.setString(2, task.getDescription());
        statement.setDate(3, new Date(task.getStartDate().getTime()));
        statement.setDate(4, new Date(task.getEndDate().getTime()));
        statement.setString(5, task.getStatus().toString());
        statement.setString(6, task.getUserId());
        statement.setString(7, task.getProjectId());
        statement.setString(8, task.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void remove(@NotNull final String id) {
        @NotNull final String query = "DELETE FROM `task_table` WHERE id = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void remove(@NotNull final List<Task> tasks) {
        tasks.forEach(e -> remove(e.getId()));
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        @NotNull final List<Task> tasksByUserId = findByUserId(userId);
        remove(tasksByUserId);
    }

    @SneakyThrows(SQLException.class)
    private Task fetch(final ResultSet row) {
        if (row == null) return null;
        @NotNull final Task task = new Task();
        task.setId(row.getString(FieldConst.ID));
        task.setCreateDate(row.getDate(FieldConst.CREATE_DATE));
        task.setName(row.getString(FieldConst.TASK_NAME));
        task.setDescription(row.getString(FieldConst.TASK_DESC));
        task.setStartDate(row.getDate(FieldConst.START_DATE));
        task.setEndDate(row.getDate(FieldConst.END_DATE));
        task.setStatus(Status.getByDisplayName(row.getString(FieldConst.TASK_STATUS)));
        task.setUserId(row.getString(FieldConst.USER_ID));
        task.setProjectId(row.getString(FieldConst.PROJECT_ID));
        return task;
    }

    @NotNull
    private List<Task> getSortTasks(final @NotNull String userId, final @NotNull String parameter) throws SQLException {
        @NotNull final String query = "SELECT * FROM `task_table` WHERE `user_id` = ? ORDER BY " + parameter;
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) tasks.add(fetch(resultSet));
        statement.close();
        return tasks;
    }

}
