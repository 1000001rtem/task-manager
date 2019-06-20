package ru.eremin.tm.backend.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eremin.tm.backend.api.service.IUserService;
import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.UserDTO;
import ru.eremin.tm.backend.model.dto.web.RegistrationRequest;
import ru.eremin.tm.backend.model.dto.web.ResultDTO;
import ru.eremin.tm.backend.model.entity.enumerated.Role;

/**
 * @autor av.eremin on 05.06.2019.
 */

@RestController
@RequestMapping(value = "/api")
public class RegistrationRestController {

    @NotNull
    @Autowired
    private IUserService userService;

    @NotNull
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO registration(@RequestBody @Nullable final RegistrationRequest registrationRequest) throws IncorrectDataException {
        if (registrationRequest == null) return new ResultDTO(false);
        @Nullable final String login = registrationRequest.getLogin();
        @Nullable final String password = registrationRequest.getPassword();
        if (login == null || login.isEmpty()) return new ResultDTO(false);
        if (password == null || password.isEmpty()) return new ResultDTO(false);

        @Nullable final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(registrationRequest.getLogin());
        userDTO.setHashPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        userDTO.setRole(Role.USER);

        userService.persist(userDTO);
        return new ResultDTO(true);
    }

}
