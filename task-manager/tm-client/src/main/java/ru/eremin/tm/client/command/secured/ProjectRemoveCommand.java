package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.exeption.IncorrectDataException;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;
import ru.eremin.tm.server.endpoint.ProjectEndpointService;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ProjectRemoveCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_remove";
    }

    @Override
    public String getDescription() {
        return "Remove selected project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException, SessionValidateExeption_Exception, IncorrectDataException_Exception {
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        @NotNull final String id = locator.getConsoleService().getStringFieldFromConsole("id");
        projectEndpoint.removeProject(locator.getSession(), id);
    }

}
