package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;
import ru.eremin.tm.server.endpoint.ProjectEndpointService;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ProjectClearCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_clear";
    }

    @Override
    public String getDescription() {
        return "Remove all project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        projectEndpoint.removeAllProjects(locator.getSession());
    }

}
