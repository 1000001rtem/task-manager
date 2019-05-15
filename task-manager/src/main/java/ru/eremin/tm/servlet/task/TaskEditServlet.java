package ru.eremin.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.enumerated.Status;
import ru.eremin.tm.service.ProjectService;
import ru.eremin.tm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 14.05.2019.
 */

@WebServlet(urlPatterns = "/enter/task-edit")
public class TaskEditServlet extends HttpServlet {

    @NotNull
    private final ITaskService taskService = TaskService.INSTANCE;

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        putProjectAttribute(req);
        putTaskAttribute(req, resp);
        req.getRequestDispatcher("/WEB-INF/pages/enter/task-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final TaskDTO task = getProject(req);
        try {
            taskService.update(task);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("task-list");
    }

    private void putTaskAttribute(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String id = req.getParameter("id");
        @Nullable TaskDTO task = null;
        try {
            task = taskService.findOne(id);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/pages/enter/task-list.jsp").forward(req, resp);
        }
        req.setAttribute("task", task);
    }

    private void putProjectAttribute(final HttpServletRequest req) {
        @NotNull final Map<String, ProjectDTO> projectIds = projectService.findAll().stream()
                .collect(Collectors.toMap(ProjectDTO::getId, projectDTO -> projectDTO));
        req.setAttribute("projects", projectIds);
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
