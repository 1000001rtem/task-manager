package ru.eremin.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * @autor Eremin Artem on 24.05.2019.
 */

@Named
@Getter
@Setter
@Scope("session")
@Component("indexController")
@ELBeanName(value = "indexController")
@Join(path = "/", to = "/index.xhtml")
public class IndexController {

    public String enter() {
        return "/WEB-INF/views/enter/general-view.xhtml?faces-redirect=true";
    }

    public String registration() {
        return "pretty:registration";
    }

}
