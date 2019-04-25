package ru.eremin.tm.server.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.api.IUserRepository;
import ru.eremin.tm.server.utils.FieldConst;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserRepository implements IUserRepository {

    @NotNull
    private final Connection connection;

    public UserRepository(@NotNull final Connection connection) {
        this.connection = connection;
    }

    @Nullable
    @Override
    @SneakyThrows
    public User findByLogin(@NotNull final String login) {
        @NotNull final String query = "SELECT * FROM `user_table` WHERE `login` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @NotNull final User user = fetch(resultSet);
        statement.close();
        return user;
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<User> findAll() {
        @NotNull final String query = "SELECT * FROM `user_table`;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<User> users = new ArrayList<>();
        while (resultSet.next()) users.add(fetch(resultSet));
        statement.close();
        return users;

    }

    @Nullable
    @Override
    @SneakyThrows
    public User findOne(@NotNull final String id) {
        @NotNull final String query = "SELECT * FROM `user_table` WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        @NotNull final User user = fetch(resultSet);
        statement.close();
        return user;

    }

    @Override
    @SneakyThrows
    public void persist(@NotNull final User user) {
        @NotNull final String query = "INSERT INTO `user_table`" + "(" +
                FieldConst.ID + ", " +
                FieldConst.CREATE_DATE + ", " +
                FieldConst.LOGIN + ", " +
                FieldConst.HASH_PASSWORD + ", " +
                FieldConst.USER_ROLE + ") " +
                "VALUES (?,?,?,?,?);";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getId());
        statement.setDate(2, new Date(user.getCreateDate().getTime()));
        statement.setString(3, user.getLogin());
        statement.setString(4, user.getHashPassword());
        statement.setString(5, user.getRole().toString());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void merge(@NotNull final User user) {
        @NotNull final User user1 = findOne(user.getId());
        if (user1 == null) persist(user);
        else update(user);
    }

    @Override
    @SneakyThrows
    public void update(@NotNull final User user) {
        @NotNull final String query = "UPDATE `user_table` SET " +
                FieldConst.LOGIN + "= ?, " +
                FieldConst.HASH_PASSWORD + "= ?, " +
                FieldConst.USER_ROLE + "= ? " +
                "WHERE `id` = ?;";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getHashPassword());
        statement.setString(3, user.getRole().toString());
        statement.setString(4, user.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void remove(@NotNull final String id) {
        @NotNull final String query = "DELETE FROM `user_table` WHERE id = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void remove(final List<User> users) {
        users.forEach(e -> remove(e.getId()));
    }

    @Nullable
    @SneakyThrows
    private User fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final User user = new User();
        user.setId(row.getString(FieldConst.ID));
        user.setCreateDate(row.getDate(FieldConst.CREATE_DATE));
        user.setLogin(row.getString(FieldConst.LOGIN));
        user.setHashPassword(row.getString(FieldConst.HASH_PASSWORD));
        user.setRole(Role.getByDisplayName(row.getString(FieldConst.USER_ROLE)));
        return user;
    }

}
