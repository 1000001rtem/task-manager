package ru.eremin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.eremin.tm.api.IUserRepository;
import ru.eremin.tm.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Repository(UserRepository.NAME)
public class UserRepository implements IUserRepository {

    public static final String NAME = "userRepository";

    @NotNull
    @PersistenceContext
    private EntityManager em;

    @Nullable
    @Override
    public User findByLogin(@NotNull final String login) {
        @NotNull final String query = "SELECT e FROM User e WHERE e.login = :login";
        @Nullable final User user = em.createQuery(query, User.class)
                .setParameter("login", login)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
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
        if (user1 != null) em.merge(user);
    }

    @Override
    public void remove(@NotNull final String id) {
        @Nullable final User user = em.find(User.class, id);
        if (user == null) return;
        em.remove(user);
    }

}
