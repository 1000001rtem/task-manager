package ru.eremin.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor av.eremin on 14.05.2019.
 */

@WebServlet(urlPatterns = "/enter/task-remove")
public class TaskRemoveServlet extends HttpServlet {

    @NotNull
    private final ITaskService taskService = TaskService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String id = req.getParameter("id");
        try {
            taskService.remove(id);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("task-list");
    }

}
