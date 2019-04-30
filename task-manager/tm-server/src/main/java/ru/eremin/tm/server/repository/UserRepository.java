package ru.eremin.tm.server.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IUserRepository;
import ru.eremin.tm.server.model.entity.User;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserRepository implements IUserRepository {

    @NotNull
    private final EntityManager em;

    public UserRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    @Nullable
    @Override
    public User findByLogin(@NotNull final String login) {
        @NotNull final String query = "SELECT e FROM User e WHERE e.login = :login";
        @Nullable final User user = em.createQuery(query, User.class)
                .setParameter("login", login)
                .getSingleResult();
        return user;
    }

    @NotNull
    @Override
    public List<User> findAll() {
        @NotNull final String query = "SELECT e FROM User e";
        return em.createQuery(query, User.class).getResultList();

    }

    @Nullable
    @Override
    public User findOne(@NotNull final String id) {
        return em.find(User.class, id);
    }

    @Override
    public void persist(@NotNull final User user) {
        em.persist(user);
    }

    @Override
    public void merge(@NotNull final User user) {
        em.merge(user);
    }

    @Override
    public void update(@NotNull final User user) {
        @Nullable final User user1 = em.find(User.class, user.getId());
        if (user1 == null) return;
        else em.merge(user);
    }

    @Override
    public void remove(@NotNull final String id) {
        @Nullable final User user = em.find(User.class, id);
        if (user == null) return;
        em.remove(user);
    }

}
