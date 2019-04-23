package ru.eremin.tm.server.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.model.repository.api.ISessionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor av.eremin on 22.04.2019.
 */

public class SessionRepository implements ISessionRepository {

    @NotNull
    private final Map<String, Session> sessions;

    public SessionRepository() {
        this.sessions = new HashMap<>();
    }

    @NotNull
    @Override
    public List<Session> findAll() {
        return new ArrayList<>(sessions.values());
    }

    @Nullable
    @Override
    public Session findOne(@NotNull final String id) {
        return sessions.get(id);
    }

    @Nullable
    @Override
    public Session findByUserId(@NotNull final String userId) {
        for (final Session session : sessions.values()) {
            if (session.getUserId().equals(userId)) return session;
        }
        return null;
    }

    @Override
    public void persist(@NotNull final Session session) {
        sessions.put(session.getId(), session);
    }

    @Override
    public void merge(final Session session) {
        sessions.put(session.getId(), session);
    }

    @Override
    public void update(final Session session) throws IncorrectDataException {
        if (sessions.get(session.getId()) == null) throw new IncorrectDataException("Session is null");
        sessions.put(session.getId(), session);
    }

    @Override
    public void remove(final String id) {
        sessions.remove(id);
    }

    @Override
    public void remove(final List<Session> sessions) {
        sessions.forEach(e -> remove(e.getId()));
    }

    @Override
    public void removeAll() {
        sessions.clear();
    }

}
