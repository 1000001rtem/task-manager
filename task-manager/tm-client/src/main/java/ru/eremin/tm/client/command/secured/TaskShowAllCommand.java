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
public class TaskShowAllCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_list";
    }

    @Override
    public String getDescription() {
        return "Show all tasks";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        @NotNull final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
        @NotNull final List<TaskDTO> taskDTOS = taskEndpoint.findAllTasks(locator.getSession());
        taskDTOS.forEach(System.out::println);
    }

}
