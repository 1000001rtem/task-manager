package ru.eremin.tm.server.model.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.model.repository.api.ISessionRepository;
import ru.eremin.tm.server.model.service.api.ISessionService;
import ru.eremin.tm.server.utils.SignatureUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 22.04.2019.
 */

public class SessionService implements ISessionService {

    @Nullable
    private ISessionRepository sessionRepository;

    public SessionService(@Nullable final ISessionRepository sessionRepository) {
        if (sessionRepository == null) return;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public @NotNull List<SessionDTO> findAll() {
        return sessionRepository.findAll().stream().map(SessionDTO::new).collect(Collectors.toList());
    }

    @Nullable
    @Override
    public SessionDTO findOne(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        @Nullable final Session session = sessionRepository.findOne(id);
        if (session == null) return null;
        return new SessionDTO(session);
    }

    @Override
    public void persist(@Nullable final SessionDTO sessionDTO) {
        if (sessionDTO == null) return;
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.persist(session);
    }

    @Override
    public void merge(@Nullable final SessionDTO sessionDTO) {
        if (sessionDTO == null) return;
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.persist(session);
    }

    @Override
    public void update(@Nullable final SessionDTO sessionDTO) {
        if (sessionDTO == null) return;
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.update(session);
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) return;
        sessionRepository.remove(id);
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return sessionRepository.findOne(id) == null;
    }

    @NotNull
    @Override
    public Session getEntity(@NotNull final SessionDTO sessionDTO) {
        @NotNull final Session session = new Session();
        session.setId(sessionDTO.getId());
        if (sessionDTO.getUserId() != null && !sessionDTO.getUserId().isEmpty())
            session.setUserId(sessionDTO.getUserId());
        if (sessionDTO.getSign() != null && !sessionDTO.getSign().isEmpty()) session.setSign(sessionDTO.getSign());
        if (session.getUserRole() != null) session.setUserRole(sessionDTO.getUserRole());
        return session;
    }

    @Override
    public String sign(@NotNull final SessionDTO sessionDTO) {
        return SignatureUtil.sign(sessionDTO, "#6df5&f", 10);
    }

}
