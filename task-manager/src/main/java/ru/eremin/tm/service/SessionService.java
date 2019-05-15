package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.ISessionRepository;
import ru.eremin.tm.api.ISessionService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.SessionDTO;
import ru.eremin.tm.model.entity.session.Session;
import ru.eremin.tm.repository.SessionRepository;
import ru.eremin.tm.utils.SignatureUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 22.04.2019.
 */

@NoArgsConstructor
public enum SessionService implements ISessionService {

    INSTANCE;

    @NotNull
    private ISessionRepository sessionRepository = SessionRepository.INSTANCE;

    @Override
    public @NotNull List<SessionDTO> findAll() {
        return sessionRepository.findAll().stream().map(SessionDTO::new).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public SessionDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Session session = sessionRepository.findOne(id);
        if (session == null) throw new IncorrectDataException("Wrong id");
        return new SessionDTO(session);
    }

    @Nullable
    @Override
    public SessionDTO findByUserId(@Nullable final String userId) throws IncorrectDataException {
        if (userId == null || userId.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Session session = sessionRepository.findByUserId(userId);
        if (session == null) return null;
        return new SessionDTO(session);
    }

    @Override
    public void persist(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.persist(session);
    }

    @Override
    public void merge(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.persist(session);
    }

    @Override
    public void update(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.update(session);
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        sessionRepository.remove(id);
    }

    @Override
    public void removeAll() {
        sessionRepository.removeAll();
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