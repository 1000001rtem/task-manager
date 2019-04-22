package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;
import ru.eremin.tm.server.endpoint.TaskEndpoint;
import ru.eremin.tm.server.endpoint.TaskEndpointService;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class TaskRemoveCommand extends AbstractTerminalCommand {

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
    public void execute() throws SessionValidateExeption_Exception, IncorrectDataException_Exception {
        @NotNull final String id = locator.getConsoleService().getStringFieldFromConsole("id");
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        @NotNull final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
        taskEndpoint.removeTask(locator.getSession(), id);
    }

}
