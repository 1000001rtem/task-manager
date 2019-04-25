package ru.eremin.tm.server.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IUserRepository;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.repository.UserRepository;
import ru.eremin.tm.server.utils.DBConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserService implements IUserService {

    public UserService(@Nullable final IUserRepository userRepository) {
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public UserDTO findByLogin(@Nullable final String login) throws IncorrectDataException {
        if (login == null || login.isEmpty()) throw new IncorrectDataException("Wrong login");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        @Nullable final User user = userRepository.findByLogin(login);
        if (user == null) {
            connection.close();
            throw new IncorrectDataException("Wrong login");
        }
        connection.commit();
        connection.close();
        return new UserDTO(user);
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public List<UserDTO> findAll() {
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final List<UserDTO> userDTOS = userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        connection.commit();
        connection.close();
        return userDTOS;
    }

    @NotNull
    @Override
    @SneakyThrows(SQLException.class)
    public UserDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        @Nullable final User user = userRepository.findOne(id);
        if (user == null) {
            connection.close();
            throw new IncorrectDataException("Wrong id");
        }
        connection.commit();
        connection.close();
        return new UserDTO(user);
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void persist(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final User user = getEntity(userDTO);
        userRepository.persist(user);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void merge(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final User user = getEntity(userDTO);
        userRepository.merge(user);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void update(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final User user = getEntity(userDTO);
        userRepository.update(user);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        userRepository.remove(id);
        connection.commit();
        connection.close();
    }

    @Override
    @SneakyThrows(SQLException.class)
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @NotNull final Connection connection = DBConnectionUtils.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        connection.setAutoCommit(false);
        @NotNull final User user = userRepository.findOne(id);
        connection.commit();
        connection.close();
        return user != null;
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
