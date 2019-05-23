package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.primefaces.event.RowEditEvent;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.enumerated.Status;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Arrays;
import java.util.List;

/**
 * @autor Eremin Artem on 22.05.2019.
 */

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "projectController")
@URLMapping(
        id = "projects",
        pattern = "/enter/projects",
        viewId = "/WEB-INF/views/enter/project-list-view.xhtml"
)
public class ProjectListController {

    @ManagedProperty(value = "#{projectService}")
    private IProjectService projectService;

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

    public void createProject() throws IncorrectDataException {
        @NotNull ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setUserId("6706e691-2f78-45ad-b021-3730c48959f0");
        projectService.persist(projectDTO);
        refresh();
    }

    public void deleteProject(@Nullable final String id) throws IncorrectDataException {
        projectService.remove(id);
        refresh();
    }

    private void refresh() {
        projects = projectService.findAll();
    }

    public void onRowCancel() {

    }

}
