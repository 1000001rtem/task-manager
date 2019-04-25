package ru.eremin.tm.server.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ISessionRepository;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.repository.SessionRepository;
import ru.eremin.tm.server.utils.DBConnectionUtils;
import ru.eremin.tm.server.utils.SignatureUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 22.04.2019.
 */

public class SessionService implements ISessionService {

    public SessionService() {
    }

    @Override
    @SneakyThrows(SQLException.class)
    public @NotNull List<SessionDTO> findAll() {
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<SessionDTO> sessionDTOS = sessionRepository.findAll()
                .stream()
                .map(SessionDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return sessionDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public SessionDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        @Nullable final Session session = sessionRepository.findOne(id);
        if (session == null) {
            connection.close();
            throw new IncorrectDataException("Wrong id");
        }
        connection.commit();
        connection.close();
        return new SessionDTO(session);
    }

    @Nullable
    @Override
    @SneakyThrows(SQLException.class)
    public SessionDTO findByUserId(@Nullable final String userId) throws IncorrectDataException {
        if (userId == null || userId.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        @Nullable final Session session = sessionRepository.findByUserId(userId);
        if (session == null) {
            connection.close();
            return null;
        }
        connection.commit();
        connection.close();
        return new SessionDTO(session);
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void persist(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.persist(session);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void merge(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.persist(session);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void update(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.update(session);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        sessionRepository.remove(id);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void removeAll() {
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        sessionRepository.removeAll();
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final Session session = sessionRepository.findOne(id);
        connection.commit();
        connection.close();
        return session != null;
    }

    @NotNull
    @Override
    public Session getEntity(@NotNull final SessionDTO sessionDTO) {
        @NotNull final Session session = new Session();
        session.setId(sessionDTO.getId());
        if (sessionDTO.getUserId() != null && !sessionDTO.getUserId().isEmpty())
            session.setUserId(sessionDTO.getUserId());
        if (sessionDTO.getSign() != null && !sessionDTO.getSign().isEmpty()) session.setSign(sessionDTO.getSign());
        if (sessionDTO.getUserRole() != null) session.setUserRole(sessionDTO.getUserRole());
        return session;
    }

    @Override
    public String sign(@NotNull final SessionDTO sessionDTO) {
        return SignatureUtil.sign(sessionDTO, "#6df5&f", 10);
    }

}
