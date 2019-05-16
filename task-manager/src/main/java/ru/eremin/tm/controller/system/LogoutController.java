package ru.eremin.tm.controller.system;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
public class LogoutController {

    @GetMapping(value = "/logout")
    public String logout(@NotNull final HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
