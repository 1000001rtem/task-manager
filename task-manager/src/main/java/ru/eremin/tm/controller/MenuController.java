package ru.eremin.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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
@ELBeanName(value = "menuController")
@Join(path = "/enter/menu", to = "/WEB-INF/views/enter/general-view.xhtml")
public class MenuController {

    private String page = "main-view";

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/logout");
    }

    public boolean checkRole() {
        @Nullable final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
    }

}
