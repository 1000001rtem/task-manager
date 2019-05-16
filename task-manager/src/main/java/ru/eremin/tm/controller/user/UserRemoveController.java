package ru.eremin.tm.controller.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eremin.tm.api.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping(value = "/enter/admin")
public class UserRemoveController {

    @NotNull
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/user-remove")
    public String removeUser(@RequestParam("id") @NotNull final String id) throws IncorrectDataException {
        userService.remove(id);
        return "redirect:/enter/admin/user-list";
    }
}
