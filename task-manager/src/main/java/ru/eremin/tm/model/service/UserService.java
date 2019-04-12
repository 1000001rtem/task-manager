package ru.eremin.tm.model.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.model.repository.api.IUserRepository;
import ru.eremin.tm.model.service.api.IUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserService implements IUserService {

    @Nullable
    private IUserRepository userRepository;

    public UserService(@Nullable final IUserRepository userRepository) {
        if (userRepository == null) return;
        this.userRepository = userRepository;
    }

    @Override
    @Nullable
    public UserDTO findByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) return null;
        @Nullable final User user = userRepository.findByLogin(login);
        if (user == null) return null;
        return new UserDTO(user);
    }

    @Override
    @NotNull
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Nullable
    public UserDTO findOne(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final User user = userRepository.findOne(id);
        if (user == null) return null;
        return new UserDTO(user);
    }

    @Override
    public void persist(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        @NotNull final User user = getEntity(userDTO);
        userRepository.persist(user);
    }

    @Override
    public void merge(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        @NotNull final User user = getEntity(userDTO);
        userRepository.persist(user);
    }

    @Override
    public void update(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        @NotNull final User user = getEntity(userDTO);
        userRepository.update(user);
    }

    @Override
    public boolean remove(@Nullable final String id) {
        if (id == null || id.isEmpty() || isExist(id)) return false;
        userRepository.remove(id);
        return false;
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return userRepository.findOne(id) == null;
    }

    @Override
    @NotNull
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
