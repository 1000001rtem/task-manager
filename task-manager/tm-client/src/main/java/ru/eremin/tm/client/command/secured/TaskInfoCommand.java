package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;
import ru.eremin.tm.server.endpoint.TaskDTO;
import ru.eremin.tm.server.endpoint.TaskEndpoint;
import ru.eremin.tm.server.endpoint.TaskEndpointService;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class TaskInfoCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_info";
    }

    @Override
    public String getDescription() {
        return "Show task information";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws SessionValidateExeption_Exception {
        @NotNull final String id = locator.getConsoleService().getStringFieldFromConsole("id");
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        @NotNull final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
        @NotNull final TaskDTO taskDTO = taskEndpoint.findOneTask(locator.getSession(), id);
        if (taskDTO == null) System.out.println("Wrong id");
        else System.out.println(taskDTO.info());
    }

}
