package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.eremin.tm.model.entity.enumerated.Role;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

/**
 * @autor Eremin Artem on 22.05.2019.
 */

@Named
@Getter
@Setter
@Scope("session")
@Component("menuController")
@URLMapping(
        id = "menu",
        pattern = "/enter/menu",
        viewId = "/WEB-INF/views/enter/general-view.xhtml"
)
public class MenuController {

    private String page = "main-view";

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/logout");
    }

    public boolean checkRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(e -> e.getAuthority().equals(Role.ADMIN));
    }

}
