package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IUserRepository;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.config.SqlSessionConfig;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserService implements IUserService {

    @Nullable
    private EntityManagerFactory entityManagerFactory;

    public UserService(@Nullable final EntityManagerFactory entityManagerFactory) {
        if(entityManagerFactory == null) return;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Nullable
    @Override
    public UserDTO findByLogin(@Nullable final String login) throws IncorrectDataException {
        if (login == null || login.isEmpty()) throw new IncorrectDataException("Wrong login");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = userRepository.findByLogin(login);
            if (user == null) throw new IncorrectDataException("Wrong login");
            em.getTransaction().commit();
            return new UserDTO(user);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @NotNull
    @Override
    public List<UserDTO> findAll() {
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final List<UserDTO> userDTOS = userRepository.findAll()
                    .stream()
                    .map(UserDTO::new)
                    .collect(Collectors.toList());
            em.getTransaction().commit();
            return userDTOS;
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
    public UserDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = userRepository.findOne(id);
            if (user == null) {
                throw new IncorrectDataException("Wrong id");
            }
            em.getTransaction().commit();
            return new UserDTO(user);
        } catch (IncorrectDataException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void persist(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final User user = getEntity(userDTO, em);
            userRepository.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            @NotNull final User user = getEntity(userDTO, em);
            userRepository.update(user);
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
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            userRepository.remove(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final EntityManager em = entityManagerFactory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        try {
            em.getTransaction().begin();
            @Nullable final User user = userRepository.findOne(id);
            em.getTransaction().commit();
            return user != null;
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
    public User getEntity(@NotNull final UserDTO userDTO, @NotNull final EntityManager em) {
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
