package ru.eremin.tm.server.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.api.ISessionRepository;
import ru.eremin.tm.server.utils.FieldConst;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor av.eremin on 22.04.2019.
 */

public class SessionRepository implements ISessionRepository {

    @NotNull
    private final Connection connection;

    public SessionRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Session> findAll() {
        @NotNull final String query = "SELECT * FROM `session_table`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Session> sessions = new ArrayList<>();
        while (resultSet.next()) sessions.add(fetch(resultSet));
        statement.close();
        return sessions;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session findOne(@NotNull final String id) {
        @NotNull final String query = "SELECT * FROM `session_table` WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @Nullable final Session session = fetch(resultSet);
        statement.close();
        return session;
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session findByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM `session_table` WHERE `user_id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @Nullable final Session session = fetch(resultSet);
        statement.close();
        return session;
    }

    @Override
    @SneakyThrows
    public void persist(@NotNull final Session session) {
        @NotNull final String query = "INSERT INTO `session_table`" + "(" +
                FieldConst.ID + ", " +
                FieldConst.CREATE_DATE + ", " +
                FieldConst.USER_ID + ", " +
                FieldConst.USER_ROLE + ", " +
                FieldConst.SIGN + ") " +
                "VALUES (?,?,?,?,?);";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, session.getId());
        statement.setDate(2, new Date(session.getCreateDate().getTime()));
        statement.setString(3, session.getUserId());
        System.out.println(session.getUserRole());
        statement.setString(4, session.getUserRole().toString());
        statement.setString(5, session.getSign());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void merge(@NotNull final Session session) {
        @Nullable final Session session1 = findOne(session.getId());
        if (session1 == null) persist(session);
        else update(session);
    }

    @Override
    @SneakyThrows
    public void update(@NotNull final Session session) {
        @NotNull final String query = "UPDATE `session_table` SET " +
                FieldConst.USER_ID + "= ?, " +
                FieldConst.USER_ROLE + "= ?, " +
                FieldConst.SIGN + "= ? " +
                "WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, session.getUserId());
        statement.setString(2, session.getUserRole().toString());
        statement.setString(3, session.getSign());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final String id) {
        @NotNull final String query = "DELETE FROM `session_table` WHERE id = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void remove(@NotNull final List<Session> sessions) {
        sessions.forEach(e -> remove(e.getId()));
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query = "DELETE FROM `session_table`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }

    @Nullable
    @SneakyThrows
    private Session fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Session session = new Session();
        session.setId(row.getString(FieldConst.ID));
        session.setCreateDate(row.getDate(FieldConst.CREATE_DATE));
        session.setUserId(row.getString(FieldConst.USER_ID));
        session.setUserRole(Role.getByDisplayName(FieldConst.USER_ROLE));
        session.setSign(row.getString(FieldConst.SIGN));
        return session;
    }

}
