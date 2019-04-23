package ru.eremin.tm.server.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.model.repository.api.IUserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserRepository implements IUserRepository {

    private final Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    @NotNull
    @Override
    public User findByLogin(@NotNull final String login) throws IncorrectDataException {
        for (final User user : users.values()) {
            if (user.getLogin().equals(login)) return user;
        }
        throw new IncorrectDataException("Wrong login");
    }

    @NotNull
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Nullable
    @Override
    public User findOne(@NotNull final String id) {
        return users.get(id);
    }

    @Override
    public void persist(@NotNull final User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void merge(@NotNull final User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void update(@NotNull final User user) throws IncorrectDataException {
        if (users.get(user.getId()) == null) throw new IncorrectDataException("Wrong id");
        users.put(user.getId(), user);
    }

    @Override
    public void remove(@NotNull final String id) {
        users.remove(id);
    }

    @Override
    public void remove(final List<User> users) {
        users.forEach(e -> remove(e.getId()));
    }

}
