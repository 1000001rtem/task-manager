package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.api.ITaskService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.entity.enumerated.Status;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @autor Eremin Artem on 22.05.2019.
 */

@Getter
@Setter
@ViewScoped
@ManagedBean(name = "tasks")
@URLMapping(
        id = "tasks",
        pattern = "/tasks",
        viewId = "/WEB-INF/views/task-list-view.xhtml"
)
public class TaskListController {

    @ManagedProperty(value = "#{taskService}")
    private ITaskService taskService;

    private List<TaskDTO> tasks;

    private TaskDTO selectedTask;

    private String projectId;

    @ManagedProperty(value = "#{projectService}")
    private IProjectService projectService;

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

    public void createTasks(@Nullable final String projectId) throws IncorrectDataException {
        System.out.println(projectId);
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setUserId("6706e691-2f78-45ad-b021-3730c48959f0");
        taskDTO.setProjectId(projectId);
        taskService.persist(taskDTO);
        refresh();
    }

    public String getProjectName(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return projects.get(id).getName();
    }

    public List<Status> getStatuses(){
        return Arrays.asList(Status.values());
    }


    private void refresh() {
        tasks = taskService.findAll();
        projects = projectService.findAll().stream().collect(Collectors.toMap(ProjectDTO::getId, e -> e));
    }

}
