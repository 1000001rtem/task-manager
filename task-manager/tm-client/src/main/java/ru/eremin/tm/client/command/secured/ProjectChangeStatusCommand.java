package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class ProjectChangeStatusCommand implements ICommand {

    @Inject
    private ProjectEndpoint projectEndpoint;

    @Inject
    private ServiceLocator locator;

    @Inject
    private ConsoleService consoleService;

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
        @NotNull final String projectId = consoleService.getStringFieldFromConsole("Project id");
        @NotNull final ProjectDTO projectDTO = projectEndpoint.findOneProject(locator.getSession(), projectId);
        @NotNull final List<Status> statuses = Arrays.stream(Status.values()).collect(Collectors.toList());
        statuses.forEach(System.out::println);
        @NotNull final String newStatus = consoleService.getStringFieldFromConsole("New status");
        for (final Status status : statuses) {
            if (newStatus.equalsIgnoreCase(status.toString())) projectDTO.setStatus(status);
        }
        projectEndpoint.updateProject(locator.getSession(), projectDTO);
    }

}
