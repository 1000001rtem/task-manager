package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.ProjectDTO;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;
import ru.eremin.tm.server.endpoint.ProjectEndpointService;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;

import java.util.Date;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ProjectCreateCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_create";
    }

    @Override
    public String getDescription() {
        return "Create new project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws SessionValidateExeption_Exception {
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        @NotNull final ProjectDTO projectDTO = getProject();
        projectEndpoint.persistProject(locator.getSession(), projectDTO);
    }

    @NotNull
    private ProjectDTO getProject() {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final String name = consoleService.getStringFieldFromConsole("Project name");
        @NotNull final String description = consoleService.getStringFieldFromConsole("Project Description");
        @NotNull final Date startDate = consoleService.getDateFieldFromConsole("Start date");
        @NotNull final Date endDate = consoleService.getDateFieldFromConsole("End date");
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName(name);
        project.setDescription(description);
        if (startDate != null) project.setStartDate(startDate);
        if (endDate != null) project.setEndDate(endDate);
        project.setUserId(locator.getSession().getUserId());
        return project;
    }

}
