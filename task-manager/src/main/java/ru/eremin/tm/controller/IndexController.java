package ru.eremin.tm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

}
