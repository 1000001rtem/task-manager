package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;

import javax.inject.Named;

/**
 * @autor Eremin Artem on 24.05.2019.
 */

@Named
@Getter
@Setter
@Scope("session")
@Component("registrationController")
@URLMapping(
        id = "registration",
        pattern = "/registration",
        viewId = "/registration-view.xhtml"
)
public class RegistrationController {

    @NotNull
    @Autowired
    private IUserService userService;

    @NotNull
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private String login;

    private String password;

    public String registration() throws IncorrectDataException {
        if (login == null || login.isEmpty()) return "pretty:registration";
        if (password == null || password.isEmpty()) return "pretty:registration";
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setHashPassword(passwordEncoder.encode(password));
        userDTO.setRole(Role.USER);
        userService.persist(userDTO);
        return "pretty:index";
    }

}
