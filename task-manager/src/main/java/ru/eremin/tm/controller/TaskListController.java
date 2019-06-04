package ru.eremin.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.eremin.tm.api.service.IProjectService;
import ru.eremin.tm.api.service.ITaskService;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.enumerated.Status;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 22.05.2019.
 */

@Getter
@Setter
@Scope("session")
@Component("taskController")
@ELBeanName(value = "taskController")
@Join(path = "/enter/tasks", to = "/WEB-INF/views/enter/task-list-view.xhtml")
public class TaskListController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    private List<TaskDTO> tasks;

    private TaskDTO selectedTask;

    private String projectId;

    private Map<String, ProjectDTO> projects;

    @PostConstruct
    public void init() {
        refresh();
    }

    public void onRowEdit(@Nullable final RowEditEvent event) throws IncorrectDataException {
        if (event == null) return;
        @Nullable final TaskDTO taskDTO = (TaskDTO) event.getObject();
        taskService.update(taskDTO);
        refresh();
    }

    public void onRowCancel() {
    }

    public void deleteTask(@Nullable final String id) throws IncorrectDataException {
        taskService.remove(id);
        refresh();
    }

    public void createTask(@Nullable final String projectId) throws IncorrectDataException, AccessForbiddenException {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setUserId(getUserId());
        taskDTO.setProjectId(projectId);
        taskService.persist(taskDTO);
        refresh();
    }

    public String getProjectName(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return projects.get(id).getName();
    }

    public List<Status> getStatuses() {
        return Arrays.asList(Status.values());
    }

    private void refresh() {
        try {
            tasks = taskService.findByUserId(getUserId());
            projects = projectService.findByUserId(getUserId()).stream().collect(Collectors.toMap(ProjectDTO::getId, e -> e));
        } catch (AccessForbiddenException e) {
            e.printStackTrace();
        }
    }

    private String getUserId() throws AccessForbiddenException {
        @Nullable final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        @Nullable final String login = authentication.getName();
        if (login == null || login.isEmpty()) throw new AccessForbiddenException("user not found");
        @Nullable final String userId = getUserIdByLogin(login);
        return userId;
    }

    @Nullable
    private String getUserIdByLogin(final @Nullable String login) {
        @Nullable String userId = null;
        try {
            userId = userService.findByLogin(login).getId();
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        return userId;
    }

}
