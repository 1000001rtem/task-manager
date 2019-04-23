package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class ProjectChangeStatusCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_change_status";
    }

    @Override
    public String getDescription() {
        return "Change status of project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String projectId = locator.getConsoleService().getStringFieldFromConsole("Project id");
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        @NotNull final ProjectDTO projectDTO = projectEndpoint.findOneProject(locator.getSession(), projectId);
        @NotNull final List<Status> statuses = Arrays.stream(Status.values()).collect(Collectors.toList());
        statuses.forEach(System.out::println);
        @NotNull final String newStatus = locator.getConsoleService().getStringFieldFromConsole("New status");
        for (final Status status : statuses) {
            if (newStatus.equalsIgnoreCase(status.toString())) projectDTO.setStatus(status);
        }
        projectEndpoint.updateProject(locator.getSession(), projectDTO);
    }

}
