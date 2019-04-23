package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.*;

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
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        @NotNull final List<ProjectDTO> projectDTOS = projectEndpoint.findAllProjects(locator.getSession());
        projectDTOS.forEach(System.out::println);
    }

}
