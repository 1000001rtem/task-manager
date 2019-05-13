package ru.eremin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.IUserRepository;
import ru.eremin.tm.api.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public enum UserService implements IUserService {

    INSTANCE;

    @NotNull
    private IUserRepository userRepository = UserRepository.INSTANCE;

    @NotNull
    @Override
    public UserDTO findByLogin(@Nullable final String login) throws IncorrectDataException {
        if (login == null || login.isEmpty()) throw new IncorrectDataException("Wrong login");
        @NotNull final User user = userRepository.findByLogin(login);
        if (user == null) throw new IncorrectDataException("Wrong login");
        return new UserDTO(user);
    }

    @NotNull
    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public UserDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final User user = userRepository.findOne(id);
        if (user == null) throw new IncorrectDataException("Wrong id");
        return new UserDTO(user);
    }

    @Override
    public void persist(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final User user = getEntity(userDTO);
        userRepository.persist(user);
    }

    @Override
    public void merge(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final User user = getEntity(userDTO);
        userRepository.persist(user);
    }

    @Override
    public void update(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final User user = getEntity(userDTO);
        userRepository.update(user);
    }

    @Override
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty() || !isExist(id)) throw new IncorrectDataException("Wrong id");
        userRepository.remove(id);
    }

    @Override
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        return userRepository.findOne(id) != null;
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
