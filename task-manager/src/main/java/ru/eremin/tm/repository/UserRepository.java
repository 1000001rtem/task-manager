package ru.eremin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.eremin.tm.api.IUserRepository;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.util.PasswordHashUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Repository(UserRepository.NAME)
public class UserRepository implements IUserRepository {

    public static final String NAME = "userRepository";

    private final Map<String, User> users;

    UserRepository() {
        this.users = new HashMap<>();

        @NotNull final User user = new User();
        user.setId("6bf0f091-e795-42d1-bb9a-77799cdf37da");
        user.setLogin("user");
        user.setHashPassword(PasswordHashUtil.md5("pass"));
        user.setRole(Role.USER);

        @NotNull final User admin = new User();
        admin.setId("6706e691-2f78-45ad-b021-3730c48959f0");
        admin.setLogin("admin");
        admin.setHashPassword(PasswordHashUtil.md5("pass"));
        admin.setRole(Role.ADMIN);

        users.put(user.getId(), user);
        users.put(admin.getId(), admin);
    }

    @Nullable
    @Override
    public User findByLogin(@NotNull final String login) {
        for (final User user : users.values()) {
            if (user.getLogin().equals(login)) return user;
        }
        return null;
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
