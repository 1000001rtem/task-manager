package ru.eremin.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor av.eremin on 13.05.2019.
 */

@WebServlet(urlPatterns = "/enter/project-remove")
public class ProjectRemoveServlet extends HttpServlet {

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String id = req.getParameter("id");
        try {
            projectService.remove(id);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("project-list");
    }

}
