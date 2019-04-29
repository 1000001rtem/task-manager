package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.config.SqlSessionConfig;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.repository.SessionRepository;
import ru.eremin.tm.server.utils.SignatureUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 22.04.2019.
 */

@NoArgsConstructor
public class SessionService implements ISessionService {

    @NotNull
    final SqlSessionFactory sessionFactory = SqlSessionConfig.getSessionFactory();

    @Override
    public @NotNull List<SessionDTO> findAll() {
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            @NotNull final List<SessionDTO> sessionDTOS = sessionRepository.findAll()
                    .stream()
                    .map(SessionDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return sessionDTOS;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public SessionDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            @Nullable final Session session = sessionRepository.findOne(id);
            if (session == null) {
                throw new IncorrectDataException("Wrong id");
            }
            sqlSession.commit();
            return new SessionDTO(session);
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    @Nullable
    @Override
    public SessionDTO findByUserId(@Nullable final String userId) throws IncorrectDataException {
        if (userId == null || userId.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            @Nullable final Session session = sessionRepository.findByUserId(userId);
            if (session == null) {
                return null;
            }
            sqlSession.commit();
            return new SessionDTO(session);
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    @Override
    public void persist(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            @NotNull final Session session = getEntity(sessionDTO);
            sessionRepository.persist(session);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(@Nullable final SessionDTO sessionDTO) throws IncorrectDataException {
        if (sessionDTO == null) throw new IncorrectDataException("Session is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            @NotNull final Session session = getEntity(sessionDTO);
            sessionRepository.update(session);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            sessionRepository.remove(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void removeAll() {
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            sessionRepository.removeAll();
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final SessionRepository sessionRepository = sqlSession.getMapper(SessionRepository.class);
            @NotNull final Session session = sessionRepository.findOne(id);
            sqlSession.commit();
            return session != null;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
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
