package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.model.service.api.IProjectService;

import java.util.List;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class ProjectFindByNameCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_find_by_name";
    }

    @Override
    public String getDescription() {
        return "Find project by name";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final List<ProjectDTO> projects = projectService.findAll(locator.getSession().getUser().getId());
        @NotNull final String key = consoleService.getStringFieldFromConsole("*** Write name or part of name of project ***");
        projects.stream().filter(e -> e.getName().contains(key)).forEach(System.out::println);
    }

}
