package ru.eremin.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @autor Eremin Artem on 24.05.2019.
 */

@Getter
@Setter
@Scope("session")
@Component("userController")
@ELBeanName(value = "userController")
@Join(path = "/enter/admin/users", to = "/WEB-INF/views/enter/user-list-view.xhtml")
public class UserController {

    @Autowired
    private IUserService userService;

    private List<UserDTO> users;

    @PostConstruct
    public void init() {
        refresh();
    }

    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    public void deleteUser(@Nullable final String id) throws IncorrectDataException {
        userService.remove(id);
        refresh();
    }

    public void onRowEdit(@Nullable final RowEditEvent event) throws IncorrectDataException {
        if (event == null) return;
        @Nullable final UserDTO userDTO = (UserDTO) event.getObject();
        userService.update(userDTO);
        refresh();
    }

    public void onRowCancel() {
    }

    private void refresh() {
        users = userService.findAll();
    }

}
