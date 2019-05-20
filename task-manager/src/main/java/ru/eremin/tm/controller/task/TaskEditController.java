package ru.eremin.tm.controller.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.enumerated.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 16.05.2019.
 */


@Controller
@RequestMapping(value = "enter")
public class TaskEditController {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/task-edit")
    public String editView(@RequestParam("id") @NotNull final String id,
                           @NotNull final Model model,
                           @NotNull final HttpSession session) throws IncorrectDataException, AccessForbiddenException {
        @Nullable final String userId = (String) session.getAttribute("userId");
        @NotNull final Map<String, ProjectDTO> projects = projectService.findByUserId(userId).stream()
                .collect(Collectors.toMap(ProjectDTO::getId, projectDTO -> projectDTO));
        model.addAttribute("projects", projects);
        @NotNull final TaskDTO task = taskService.findOne(id);
        model.addAttribute("task", task);
        model.addAttribute("statuses", Status.values());
        return "enter/task-edit";
    }

    @PostMapping(value = "/task-edit")
    public String editTask(@NotNull final HttpServletRequest request) throws IncorrectDataException {
        @NotNull final TaskDTO task = getProject(request);
        taskService.update(task);
        return "redirect:/enter/task-list";
    }

    @Nullable
    private TaskDTO getProject(final HttpServletRequest req) {
        @Nullable TaskDTO taskDTO = null;
        try {
            taskDTO = taskService.findOne(req.getParameter("id"));
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        if (taskDTO == null) return null;
        @Nullable final String taskName = req.getParameter("name");
        @Nullable final String taskDescription = req.getParameter("description");
        @Nullable final String taskStartDateString = req.getParameter("startDate");
        @Nullable final String taskEndDateString = req.getParameter("endDate");
        @Nullable final Status taskStatus = Status.getByDisplayName(req.getParameter("status"));
        @Nullable final String taskProjectId = req.getParameter("projectId");
        @Nullable final Date startDate = getDate(taskStartDateString);
        @Nullable final Date endDate = getDate(taskEndDateString);
        if (taskName != null && !taskName.isEmpty()) taskDTO.setName(taskName);
        if (taskDescription != null && !taskDescription.isEmpty()) taskDTO.setDescription(taskDescription);
        if (startDate != null) taskDTO.setStartDate(startDate);
        if (endDate != null) taskDTO.setEndDate(endDate);
        if (taskStatus != null) taskDTO.setStatus(taskStatus);
        if (taskProjectId != null && !taskProjectId.isEmpty()) taskDTO.setProjectId(taskProjectId);
        return taskDTO;
    }

    @Nullable
    private Date getDate(final String dateString) {
        if (dateString == null || dateString.isEmpty()) return null;
        try {
            final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
