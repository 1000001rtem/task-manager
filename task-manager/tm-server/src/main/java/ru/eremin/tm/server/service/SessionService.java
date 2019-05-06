package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ISessionRepository;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.repository.SessionRepository;
import ru.eremin.tm.server.utils.SignatureUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 22.04.2019.
 */

@ApplicationScoped
@NoArgsConstructor
public class SessionService implements ISessionService {

    @Inject
    @Nullable
    private EntityManagerFactory entityManagerFactory;

    @NotNull
    @Override
    public List<SessionDTO> findAll() {
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final List<SessionDTO> sessionDTOS = sessionRepository.findAll()
                    .stream()
                    .map(SessionDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return sessionDTOS;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public SessionDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final Session session = sessionRepository.findOne(id);
            if (session == null) throw new IncorrectDataException("Wrong id");
            em.getTransaction().commit();
            return new SessionDTO(session);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Nullable
    @Override
    public SessionDTO findByUserId(@Nullable final String userId) throws IncorrectDataException {
        if (userId == null || userId.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final Session session = sessionRepository.findByUserId(userId);
            if (session == null) return null;
            em.getTransaction().commit();
            return new SessionDTO(session);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void persist(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Session session = getEntity(sessionDTO, em);
            sessionRepository.persist(session);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Session session = getEntity(sessionDTO, em);
            sessionRepository.update(session);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Session session = getEntity(sessionDTO, em);
            sessionRepository.merge(session);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            sessionRepository.remove(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void removeAll() {
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            sessionRepository.removeAll();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final Session session = sessionRepository.findOne(id);
            em.getTransaction().commit();
            return session != null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @NotNull
    @Override
    public Session getEntity(@NotNull final SessionDTO sessionDTO, @NotNull final EntityManager em) {
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
