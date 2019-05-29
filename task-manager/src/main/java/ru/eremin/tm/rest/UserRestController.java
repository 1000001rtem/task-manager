package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ResultDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.dto.ChangePasswordDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.util.PasswordHashUtil;

import java.util.List;

/**
 * @autor av.eremin on 28.05.2019.
 */

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO findOneUser(@RequestParam(name = "userId") @Nullable final String userId) throws IncorrectDataException {
        return userService.findOne(userId);
    }

    @GetMapping(value = "/findByLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO findUserByLogin(@RequestParam(name = "userLogin") @Nullable final String userLogin) throws IncorrectDataException {
        return userService.findByLogin(userLogin);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO createUser(@RequestBody @Nullable final UserDTO userDTO) throws IncorrectDataException {
        @NotNull final UserDTO userDTO1 = new UserDTO();
        userDTO1.setLogin(userDTO.getLogin());
        userDTO1.setHashPassword(passwordEncoder.encode(userDTO.getHashPassword()));
        userDTO1.setRole(Role.USER);
        userService.persist(userDTO1);
        return new ResultDTO(true);
    }

    @PutMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO changePassword(@RequestBody @Nullable ChangePasswordDTO changePasswordDTO) throws IncorrectDataException {
        if (changePasswordDTO == null) return new ResultDTO(false);
        @Nullable final String userId = changePasswordDTO.getUserId();
        @Nullable final String oldPassword = passwordEncoder.encode(changePasswordDTO.getOldPassword());
        @Nullable final String newPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
        @NotNull final UserDTO userDTO = userService.findOne(userId);
        if (!userDTO.getHashPassword().equals(oldPassword)) return new ResultDTO(false);
        userDTO.setHashPassword(newPassword);
        userService.update(userDTO);
        return new ResultDTO(true);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO updateUser(@RequestBody @Nullable final UserDTO userDTO) throws IncorrectDataException {
        userService.update(userDTO);
        return new ResultDTO(true);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO deleteUser(@RequestParam(name = "userId") @Nullable final String userId) throws IncorrectDataException {
        userService.remove(userId);
        return new ResultDTO(true);
    }

}
