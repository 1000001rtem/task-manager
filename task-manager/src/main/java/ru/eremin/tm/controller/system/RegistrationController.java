package ru.eremin.tm.controller.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eremin.tm.api.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.util.PasswordHashUtil;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@Controller
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/registration")
    public String registrationView() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@RequestParam("login") @Nullable String login,
                               @RequestParam("password") @Nullable String password) throws IncorrectDataException {
        if (login == null || login.isEmpty()) return "redirect:/registration";
        if (password == null || password.isEmpty()) return "redirect:/registration";
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setHashPassword(PasswordHashUtil.md5(password));
        userDTO.setRole(Role.USER);
        userService.persist(userDTO);
        return "redirect:/login";
    }

}
