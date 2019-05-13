package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.Session;
import ru.eremin.tm.server.repository.SessionRepository;
import ru.eremin.tm.server.utils.SignatureUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 22.04.2019.
 */

@NoArgsConstructor
@Service(SessionService.NAME)
public class SessionService implements ISessionService {

    public static final String NAME = "sessionService";

    @NotNull
    @Autowired
    private SessionRepository sessionRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<SessionDTO> findAll() {
        return sessionRepository.findAll()
                .stream()
                .map(SessionDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public SessionDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Session session = sessionRepository.findById(id).orElseThrow(() -> new IncorrectDataException("Wrong id"));
        return new SessionDTO(session);
    }

    @Nullable
    @Override
    @Transactional(readOnly = true)
    public SessionDTO findByUserId(@Nullable final String userId) throws IncorrectDataException {
        if (userId == null || userId.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final Session session = sessionRepository.findByUserId(userId);
        if (session == null) return null;
        return new SessionDTO(session);
    }

    @Override
    @Transactional
    public void persist(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.save(session);
    }

    @Override
    @Transactional
    public void update(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        if (!isExist(sessionDTO.getId())) throw new IncorrectDataException("Session is not exist");
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.save(session);
    }

    @Override
    @Transactional
    public void merge(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final Session session = getEntity(sessionDTO);
        sessionRepository.save(session);
    }

    @Override
    @Transactional
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null) throw new IncorrectDataException("Wrong id");
        @Nullable final Session session = sessionRepository.findById(id).orElseThrow(() -> new IncorrectDataException("Wrong id"));
        sessionRepository.delete(session);
    }

    @Override
    @Transactional
    public void removeAll() {
        sessionRepository.removeAll();
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @Nullable final Session session = sessionRepository.findById(id).orElse(null);
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
