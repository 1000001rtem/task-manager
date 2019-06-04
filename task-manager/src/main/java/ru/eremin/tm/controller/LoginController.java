package ru.eremin.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.eremin.tm.api.service.IAuthService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;

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
@ELBeanName(value = "indexController")
@Join(path = "/login", to = "/login-view.xhtml")
public class LoginController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private String userLogin;

    private String password;

    public String auth() throws IncorrectDataException {
        @NotNull final UserDTO userDTO = authService.check(userLogin, passwordEncoder.encode(password));
        if (userDTO == null) return "/login-view.xhtml?faces-redirect=true";
        @NotNull final FacesContext facesContext = FacesContext.getCurrentInstance();
        @NotNull final Map<String, Object> map = facesContext.getExternalContext().getSessionMap();
        map.put("auth", true);
        map.put("userId", userDTO.getId());
        map.put("userRole", userDTO.getRole());
        return "/WEB-INF/views/enter/general-view.xhtml?faces-redirect=true";
    }

}
