package ru.eremin.tm.server.model.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    @Override
    public void persist(@NotNull final Session session) {
        sessions.put(session.getId(), session);
    }

    @Override
    public void merge(final Session session) {
        sessions.put(session.getId(), session);
    }

    @Override
    public void update(final Session session) {
        if (sessions.get(session.getId()) == null) return;
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

}
