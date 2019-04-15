package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.service.api.IProjectService;

import java.util.List;

/**
 * @autor av.eremin on 15.04.2019.
 */
@NoArgsConstructor
public class ProjectSortByStatusCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_sort_by_status";
    }

    @Override
    public String getDescription() {
        return "Project by status";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() {
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final String userId = locator.getSession().getUser().getId();
        @NotNull final List<ProjectDTO> projects = projectService.findAllSortedByStatus(userId);
        projects.forEach(System.out::println);
    }

}
