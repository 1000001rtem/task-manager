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
public class ProjectSortByStartDateCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_sort_by_start";
    }

    @Override
    public String getDescription() {
        return "Projects by start date";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() {
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final String userId = locator.getSession().getUser().getId();
        @NotNull final List<ProjectDTO> projects = projectService.findAllSortedByStartDate(userId);
        projects.forEach(System.out::println);
    }

}
