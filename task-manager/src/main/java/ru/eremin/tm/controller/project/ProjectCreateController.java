package ru.eremin.tm.controller.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.enumerated.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@Controller
@RequestMapping("/enter")
public class ProjectCreateController {

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "project-create")
    public String createView() {
        return "enter/project-create";
    }

    @PostMapping(value = "project-create")
    public String createProject(@NotNull final HttpServletRequest request) throws IncorrectDataException {
        @NotNull final ProjectDTO projectDTO = getProject(request);
        projectService.persist(projectDTO);
        return "redirect:/enter/project-list";
    }

    @NotNull
    private ProjectDTO getProject(final HttpServletRequest request) {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        @Nullable final String projectName = request.getParameter("name");
        @Nullable final String projectDescription = request.getParameter("description");
        @Nullable final String projectStartDateString = request.getParameter("startDate");
        @Nullable final String projectEndDateString = request.getParameter("endDate");
        @Nullable final Date projectStartDate = getDate(projectStartDateString);
        @Nullable final Date projectEndDate = getDate(projectEndDateString);
        if (projectName != null && !projectName.isEmpty()) projectDTO.setName(projectName);
        if (projectDescription != null && !projectDescription.isEmpty()) projectDTO.setDescription(projectDescription);
        if (projectStartDate != null) projectDTO.setStartDate(projectStartDate);
        if (projectEndDate != null) projectDTO.setEndDate(projectEndDate);
        @Nullable final String userId = (String) request.getSession().getAttribute("userId");
        projectDTO.setUserId(userId);
        return projectDTO;
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
