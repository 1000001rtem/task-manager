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
public class TaskChangeStatusCommand implements ICommand {

    @Inject
    private TaskEndpoint taskEndpoint;

    @Inject
    private ServiceLocator locator;

    @Inject
    private ConsoleService consoleService;

    @Override
    public String getName() {
        return "task_change_status";
    }

    @Override
    public String getDescription() {
        return "Change status of task";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String projectId = consoleService.getStringFieldFromConsole("Task id");
        @NotNull final TaskDTO taskDTO = taskEndpoint.findOneTask(locator.getSession(), projectId);
        @NotNull final List<Status> statuses = Arrays.stream(Status.values()).collect(Collectors.toList());
        statuses.forEach(System.out::println);
        @NotNull final String newStatus = consoleService.getStringFieldFromConsole("New status");
        for (final Status status : statuses) {
            if (newStatus.equalsIgnoreCase(status.toString())) {
                taskDTO.setStatus(status);
            }
        }
        taskEndpoint.updateTask(locator.getSession(), taskDTO);
    }

}


