package ru.eremin.tm.controller.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.exeption.IncorrectDataException;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping(value = "/enter")
public class ProjectRemoveController {

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/project-remove")
    public String projectRemove(@RequestParam("id") @Nullable final String id) throws IncorrectDataException {
        projectService.remove(id);
        return "redirect:/enter/project-list";
    }

}
