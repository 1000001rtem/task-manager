package ru.eremin.tm.controller.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eremin.tm.api.IUserService;
import ru.eremin.tm.model.dto.UserDTO;

import java.util.List;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping(value = "/enter/admin")
public class UserListController {

    @NotNull
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/user-list")
    public String userList(@NotNull final Model model) {
        @NotNull final List<UserDTO> users = userService.findAll();
        model.addAttribute("users", users);
        return "enter/user-list";
    }

}
