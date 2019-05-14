package ru.eremin.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor av.eremin on 13.05.2019.
 */

@WebServlet(urlPatterns = "/enter/project-create")
public class ProjectCreateServlet extends HttpServlet {

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/enter/project-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final ProjectDTO projectDTO = getProject(req);
        try {
            projectService.persist(projectDTO);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("project-list");
    }

    @NotNull
    private ProjectDTO getProject(final HttpServletRequest req) {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        @Nullable final String projectName = req.getParameter("name");
        @Nullable final String projectDescription = req.getParameter("description");
        @Nullable final String projectStartDateString = req.getParameter("startDate");
        @Nullable final String projectEndDateString = req.getParameter("endDate");
        @Nullable final Date projectStartDate = getDate(projectStartDateString);
        @Nullable final Date projectEndDate = getDate(projectEndDateString);
        if (projectName != null && !projectName.isEmpty()) projectDTO.setName(projectName);
        if (projectDescription != null && !projectDescription.isEmpty()) projectDTO.setDescription(projectDescription);
        if (projectStartDate != null) projectDTO.setStartDate(projectStartDate);
        if (projectEndDate != null) projectDTO.setEndDate(projectEndDate);
        @Nullable final String userId = (String) req.getSession().getAttribute("userId");
        projectDTO.setUserId(userId);
        return projectDTO;
    }

    @Nullable
    private Date getDate(final String dateString) {
        try {
            final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
