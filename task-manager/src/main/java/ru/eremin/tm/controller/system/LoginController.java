package ru.eremin.tm.controller.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eremin.tm.api.IAuthService;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.util.PasswordHashUtil;

import javax.servlet.http.HttpSession;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
public class LoginController {

    @NotNull
    @Autowired
    private IAuthService authService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "login")
    public String auth(@RequestParam("login") @Nullable final String login,
                       @RequestParam("password") @Nullable final String password,
                       @NotNull final HttpSession session) {
        @Nullable final UserDTO userDTO = authService.check(login, PasswordHashUtil.md5(password));
        if (userDTO == null) return "redirect:/login";
        session.setAttribute("auth", true);
        session.setAttribute("userId", userDTO.getId());
        session.setAttribute("userRole", userDTO.getRole());
        return "redirect:/enter/project-list";
    }

}
