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
public class TaskShowInProjectCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_in_project";
    }

    @Override
    public String getDescription() {
        return "Show all tasks in selected project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        @NotNull final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
        @NotNull final String projectId = locator.getConsoleService().getStringFieldFromConsole("Project id");
        @NotNull final List<TaskDTO> taskDTOS = taskEndpoint.findTaskByProjectId(locator.getSession(), projectId);
        taskDTOS.forEach(System.out::println);
    }

}
