package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.config.SqlSessionConfig;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserService implements IUserService {

    @NotNull
    private final SqlSessionFactory sessionFactory = SqlSessionConfig.getSessionFactory();

    @Nullable
    @Override
    public UserDTO findByLogin(@Nullable final String login) throws IncorrectDataException {
        if (login == null || login.isEmpty()) throw new IncorrectDataException("Wrong login");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            @Nullable final User user = userRepository.findByLogin(login);
            if (user == null) {
                throw new IncorrectDataException("Wrong login");
            }
            sqlSession.commit();
            return new UserDTO(user);
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    @NotNull
    @Override
    public List<UserDTO> findAll() {
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            @NotNull final List<UserDTO> userDTOS = userRepository.findAll()
                    .stream()
                    .map(UserDTO::new)
                    .collect(Collectors.toList());
            sqlSession.commit();
            return userDTOS;
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
    public UserDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            @Nullable final User user = userRepository.findOne(id);
            if (user == null) {
                throw new IncorrectDataException("Wrong id");
            }
            sqlSession.commit();
            return new UserDTO(user);
        } catch (IncorrectDataException e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    @Override
    public void persist(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            @NotNull final User user = getEntity(userDTO);
            userRepository.persist(user);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            @NotNull final User user = getEntity(userDTO);
            userRepository.update(user);
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
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final SqlSession sqlSession = sessionFactory.openSession();
        try {
            @NotNull final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            userRepository.remove(id);
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
        @NotNull final User user;
        try {
            @NotNull final UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
            user = userRepository.findOne(id);
            sqlSession.commit();
            return user != null;
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
    public User getEntity(@NotNull final UserDTO userDTO) {
        @NotNull final User user = new User();
        user.setId(userDTO.getId());
        if (userDTO.getLogin() != null && !userDTO.getLogin().isEmpty()) user.setLogin(userDTO.getLogin());
        if (userDTO.getHashPassword() != null && !userDTO.getHashPassword().isEmpty()) {
            user.setHashPassword(userDTO.getHashPassword());
        }
        if (userDTO.getRole() != null) user.setRole(userDTO.getRole());
        return user;
    }

}
