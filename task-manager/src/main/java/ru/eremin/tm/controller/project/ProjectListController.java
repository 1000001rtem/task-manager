package ru.eremin.tm.controller.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.model.dto.ProjectDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping(value = "/enter")
public class ProjectListController {

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/project-list")
    public String projectList(@NotNull final HttpSession session,
                              @NotNull final Model model) {
        @Nullable final String userId = (String) session.getAttribute("userId");
        @NotNull final List<ProjectDTO> projects = projectService.findByUserId(userId);
        model.addAttribute("projects", projects);
        return "enter/project-list";
    }

}
