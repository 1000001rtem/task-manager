package ru.eremin.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @autor av.eremin on 14.05.2019.
 */

@WebServlet("/enter/task-list")
public class TaskListServlet extends HttpServlet {

    @NotNull
    private final ITaskService taskService = TaskService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final List<TaskDTO> tasks = taskService.findAll();
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/WEB-INF/pages/enter/task-list.jsp").forward(req, resp);
    }

}
