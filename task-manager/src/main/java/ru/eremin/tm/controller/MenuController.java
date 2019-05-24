package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eremin.tm.model.entity.enumerated.Role;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Map;

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

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "pretty:index";
    }

    public boolean checkRole() {
        @NotNull final FacesContext facesContext = FacesContext.getCurrentInstance();
        @NotNull final Map<String, Object> map = facesContext.getExternalContext().getSessionMap();
        @Nullable final Role userRole = (Role) map.get("userRole");
        return userRole.equals(Role.ADMIN);
    }

}
