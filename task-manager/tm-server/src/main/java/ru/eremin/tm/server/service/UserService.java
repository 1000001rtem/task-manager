package ru.eremin.tm.server.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 12.04.2019.
 */

@ApplicationScoped
@NoArgsConstructor
public class UserService implements IUserService {

    @Inject
    @NotNull
    private UserRepository userRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public UserDTO findByLogin(@Nullable final String login) throws IncorrectDataException {
        if (login == null || login.isEmpty()) throw new IncorrectDataException("Wrong login");
        @Nullable final User user = userRepository.findByLogin(login);
        if (user == null) throw new IncorrectDataException("Wrong login");
        return new UserDTO(user);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        @NotNull final List<UserDTO> userDTOS = userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        return userDTOS;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public UserDTO findOne(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final User user = userRepository.findBy(id);
        if (user == null) throw new IncorrectDataException("Wrong id");
        return new UserDTO(user);
    }

    @Override
    @Transactional
    public void persist(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final User user = getEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        if (!isExist(userDTO.getId())) throw new IncorrectDataException("User is not exist");
        @NotNull final User user = getEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void merge(@Nullable final UserDTO userDTO) throws IncorrectDataException {
        if (userDTO == null) throw new IncorrectDataException("User is null");
        @NotNull final User user = getEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void remove(@Nullable final String id) throws IncorrectDataException {
        if (id == null || id.isEmpty()) throw new IncorrectDataException("Wrong id");
        @Nullable final User user = userRepository.findBy(id);
        if (user == null) throw new IncorrectDataException("Wrong id");
        userRepository.remove(user);
    }

    @Override
    @Transactional
    public boolean isExist(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        @Nullable final User user = userRepository.findBy(id);
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
