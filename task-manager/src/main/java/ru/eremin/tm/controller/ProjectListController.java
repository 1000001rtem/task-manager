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
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.enumerated.Status;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @autor Eremin Artem on 22.05.2019.
 */

@Getter
@Setter
@Scope(value = "session")
@Component(value = "projectController")
@ELBeanName(value = "projectController")
@Join(path = "/enter/projects", to = "/WEB-INF/views/enter/project-list-view.xhtml")
public class ProjectListController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    private List<ProjectDTO> projects;

    private ProjectDTO selectedProject;

    @PostConstruct
    public void init() {
        refresh();
    }

    public List<Status> getStatuses() {
        return Arrays.asList(Status.values());
    }

    public void onRowEdit(@Nullable final RowEditEvent event) throws IncorrectDataException {
        if (event == null) return;
        @Nullable final ProjectDTO projectDTO = (ProjectDTO) event.getObject();
        projectService.update(projectDTO);
        refresh();
    }

    public void createProject() throws IncorrectDataException, AccessForbiddenException {
        @NotNull ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setUserId(getUserId());
        projectService.persist(projectDTO);
        refresh();
    }

    public void deleteProject(@Nullable final String id) throws IncorrectDataException {
        projectService.remove(id);
        refresh();
    }

    private void refresh() {
        try {
            projects = projectService.findByUserId(getUserId());
        } catch (AccessForbiddenException e) {
            e.printStackTrace();
        }
    }

    private String getUserId() throws AccessForbiddenException {
        @Nullable final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        @Nullable final String login = authentication.getName();
        if (login == null || login.isEmpty()) throw new AccessForbiddenException("user not found");
        @Nullable String userId = null;
        userId = getUserIdByLogin(login);
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

    public void onRowCancel() {

    }

}
