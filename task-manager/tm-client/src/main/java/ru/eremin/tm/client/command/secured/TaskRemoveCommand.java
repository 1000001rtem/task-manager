package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.TaskEndpoint;
import ru.eremin.tm.server.endpoint.TaskEndpointService;

import javax.inject.Inject;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class TaskRemoveCommand implements ICommand {

    @Inject
    private TaskEndpoint taskEndpoint;

    @Inject
    private ServiceLocator locator;

    @Inject
    private ConsoleService consoleService;

    @Override
    public String getName() {
        return "task_remove";
    }

    @Override
    public String getDescription() {
        return "Remove selected task";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String id = consoleService.getStringFieldFromConsole("id");
        taskEndpoint.removeTask(locator.getSession(), id);
    }

}
