package ru.eremin.tm.server.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ISessionRepository;
import ru.eremin.tm.server.model.entity.session.Session;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @autor av.eremin on 22.04.2019.
 */

public class SessionRepository implements ISessionRepository {

    @NotNull
    private final EntityManager em;

    public SessionRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    @NotNull
    @Override
    public List<Session> findAll() {
        @NotNull final List<Session> sessions = em.createQuery("SELECT e FROM Session e", Session.class).getResultList();
        return sessions;
    }

    @Nullable
    @Override
    public Session findOne(@NotNull final String id) {
        return em.find(Session.class, id);
    }

    @Nullable
    @Override
    public Session findByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT e FROM Session e WHERE e.userId = :userId";
        @Nullable final Session session = em.createQuery(query, Session.class)
                .setParameter("userId", userId)
                .getSingleResult();
        return session;
    }

    @Override
    public void persist(@NotNull final Session session) {
        em.persist(session);
    }

    @Override
    public void merge(@NotNull final Session session) {
        em.merge(session);
    }

    @Override
    public void update(@NotNull final Session session) {
        @Nullable final Session session1 = em.find(Session.class, session.getId());
        if (session1 == null) return;
        else em.merge(session);
    }

    @Override
    public void remove(@NotNull final String id) {
        @Nullable final Session session = em.find(Session.class, id);
        if (session == null) return;
        em.remove(session);
    }

    @Override
    public void removeAll() {
        @NotNull final List<Session> sessions = em.createQuery("SELECT e FROM Session e", Session.class).getResultList();
        sessions.forEach(em::remove);
    }

}
