package ru.eremin.tm.controller.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping(value = "/enter")
public class TaskListController {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/task-list")
    public String taskList(@NotNull final HttpSession session,
                           @NotNull final Model model) throws AccessForbiddenException {
        @Nullable final String userId = (String) session.getAttribute("userId");
        @NotNull final Map<String, ProjectDTO> projects = projectService.findByUserId(userId).stream()
                .collect(Collectors.toMap(ProjectDTO::getId, projectDTO -> projectDTO));
        model.addAttribute("projects", projects);

        @NotNull final List<TaskDTO> tasks = taskService.findByUserId(userId);
        model.addAttribute("tasks", tasks);
        return "enter/task-list";
    }

}
