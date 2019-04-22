package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.ProjectDTO;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;
import ru.eremin.tm.server.endpoint.ProjectEndpointService;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;

/**
 * @autor av.eremin on 10.04.2019.
 */
@NoArgsConstructor
public class ProjectInfoCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_info";
    }

    @Override
    public String getDescription() {
        return "Show project information";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws SessionValidateExeption_Exception {
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        @NotNull final String id = locator.getConsoleService().getStringFieldFromConsole("id");
        @NotNull final ProjectDTO projectDTO = projectEndpoint.findOneProject(locator.getSession(), id);
        if (projectDTO == null) System.out.println("Wrong id");
        else System.out.println(projectDTO.info());
    }

}
