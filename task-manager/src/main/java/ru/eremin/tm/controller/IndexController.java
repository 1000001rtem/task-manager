package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * @autor Eremin Artem on 22.05.2019.
 */

@Named
@Getter
@Setter
@Scope("session")
@Component("index")
@URLMapping(
        id = "index",
        pattern = "/",
        viewId = "/index.xhtml"
)
public class IndexController {

    private String page = "main-view";

}
