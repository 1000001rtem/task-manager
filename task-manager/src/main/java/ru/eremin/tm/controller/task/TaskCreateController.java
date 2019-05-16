package ru.eremin.tm.controller.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping("/enter")
public class TaskCreateController {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/task-create")
    public String createView(@NotNull final Model model,
                             @NotNull final HttpSession session) throws IncorrectDataException {
        @Nullable final String userId = (String) session.getAttribute("userId");
        @NotNull final List<ProjectDTO> projects = projectService.findByUserId(userId);
        model.addAttribute("projects", projects);
        return "enter/task-create";
    }

    @PostMapping(value = "/task-create")
    public String createTask(@NotNull final HttpServletRequest request) throws IncorrectDataException {
        @NotNull final TaskDTO taskDTO = getTask(request);
        taskService.persist(taskDTO);
        return "redirect:/enter/task-list";
    }

    @NotNull
    private TaskDTO getTask(final HttpServletRequest req) {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        @Nullable final String taskName = req.getParameter("name");
        @Nullable final String taskDescription = req.getParameter("description");
        @Nullable final String taskStartDateString = req.getParameter("startDate");
        @Nullable final String taskEndDateString = req.getParameter("endDate");
        @Nullable final String taskProjectId = req.getParameter("projectId");
        @Nullable final Date startDate = getDate(taskStartDateString);
        @Nullable final Date endDate = getDate(taskEndDateString);
        if (taskName != null && !taskName.isEmpty()) taskDTO.setName(taskName);
        if (taskDescription != null && !taskDescription.isEmpty()) taskDTO.setDescription(taskDescription);
        if (startDate != null) taskDTO.setStartDate(startDate);
        if (endDate != null) taskDTO.setEndDate(endDate);
        if (taskProjectId != null && !taskProjectId.isEmpty()) taskDTO.setProjectId(taskProjectId);
        @Nullable final String userId = (String) req.getSession().getAttribute("userId");
        taskDTO.setUserId(userId);
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
