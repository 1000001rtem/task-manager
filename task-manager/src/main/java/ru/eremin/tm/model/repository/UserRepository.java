package ru.eremin.tm.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.model.repository.api.IUserRepository;

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

    @Override
    @Nullable
    public User findByLogin(@NotNull final String login) {
        for (final User user : users.values()) {
            if (user.getLogin().equals(login)) return user;
        }
        return null;
    }

    @Override
    @NotNull
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    @Nullable
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
    public void update(@NotNull final User user) {
        if (users.get(user.getId()) == null) return;
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

    @Override
    public void removeAll() {
        users.clear();
    }

}
