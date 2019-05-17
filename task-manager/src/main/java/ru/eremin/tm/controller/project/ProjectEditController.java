package ru.eremin.tm.controller.project;

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
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.enumerated.Status;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping(value = "/enter")
public class ProjectEditController {

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/project-edit")
    public String editView(@RequestParam("id") @NotNull final String id,
                           @NotNull final Model model) throws IncorrectDataException {
        @NotNull final ProjectDTO project = projectService.findOne(id);
        model.addAttribute("project", project);
        return "enter/project-edit";
    }


    @PostMapping(value = "/project-edit")
    public String editProject(@NotNull final HttpServletRequest request) throws IncorrectDataException {
        @NotNull final ProjectDTO project = getProject(request);
        projectService.update(project);
        return "redirect:/enter/project-list";
    }

    @Nullable
    private ProjectDTO getProject(final HttpServletRequest req) {
        @Nullable ProjectDTO projectDTO = null;
        try {
            projectDTO = projectService.findOne(req.getParameter("id"));
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        if (projectDTO == null) return null;
        @Nullable final String projectName = req.getParameter("name");
        @Nullable final String projectDescription = req.getParameter("description");
        @Nullable final String projectStartDateString = req.getParameter("startDate");
        @Nullable final String projectEndDateString = req.getParameter("endDate");
        @Nullable final Status projectStatus = Status.getByDisplayName(req.getParameter("status"));
        @Nullable final Date projectStartDate = getDate(projectStartDateString);
        @Nullable final Date projectEndDate = getDate(projectEndDateString);
        if (projectName != null && !projectName.isEmpty()) projectDTO.setName(projectName);
        if (projectDescription != null && !projectDescription.isEmpty()) projectDTO.setDescription(projectDescription);
        if (projectStartDate != null) projectDTO.setStartDate(projectStartDate);
        if (projectEndDate != null) projectDTO.setEndDate(projectEndDate);
        if (projectStatus != null) projectDTO.setStatus(projectStatus);
        @Nullable final String userId = (String) req.getSession().getAttribute("userId");
        projectDTO.setUserId(userId);
        return projectDTO;
    }

    @Nullable
    private Date getDate(final String dateString) {
        if (dateString == null || dateString.isEmpty()) return null;
        try {
            @Nullable final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
