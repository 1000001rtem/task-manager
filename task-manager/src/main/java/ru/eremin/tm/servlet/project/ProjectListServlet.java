package ru.eremin.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @autor av.eremin on 13.05.2019.
 */

@WebServlet(urlPatterns = "/enter/project-list")
public class ProjectListServlet extends HttpServlet {

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final String userId = (String) req.getSession().getAttribute("userId");
        @NotNull final List<ProjectDTO> projects = projectService.findByUserId(userId);
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/pages/enter/project-list.jsp").forward(req, resp);
    }

}
