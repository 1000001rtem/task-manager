package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eremin.tm.api.IAuthService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.util.PasswordHashUtil;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Map;

/**
 * @autor Eremin Artem on 23.05.2019.
 */

@Named
@Getter
@Setter
@Scope("session")
@Component("loginController")
@URLMapping(
        id = "login",
        pattern = "/login",
        viewId = "/login-view.xhtml"
)
public class LoginController {

    @Autowired
    private IAuthService authService;

    private String login;

    private String password;

    public String auth() throws IncorrectDataException {
        @NotNull final UserDTO userDTO = authService.check(login, PasswordHashUtil.md5(password));
        System.out.println(userDTO);
        if (userDTO == null) return "login-view";
        @NotNull final FacesContext facesContext = FacesContext.getCurrentInstance();
        @NotNull final Map<String, Object> map = facesContext.getExternalContext().getSessionMap();
        map.put("auth", true);
        map.put("userId", userDTO.getId());
        map.put("userRole", userDTO.getRole());
        return "pretty:menu";
    }

}
