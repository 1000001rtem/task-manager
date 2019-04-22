package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.ProjectDTO;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;
import ru.eremin.tm.server.endpoint.ProjectEndpointService;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ProjectShowAllCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_list";
    }

    @Override
    public String getDescription() {
        return "Show all Projects";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws SessionValidateExeption_Exception {
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        @NotNull final List<ProjectDTO> projectDTOS = projectEndpoint.findAllProjects(locator.getSession());
        projectDTOS.forEach(System.out::println);
    }

}
