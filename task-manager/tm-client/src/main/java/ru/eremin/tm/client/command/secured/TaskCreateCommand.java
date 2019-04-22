package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.*;

import java.util.Date;
import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class TaskCreateCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_create";
    }

    @Override
    public String getDescription() {
        return "Create new task";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws SessionValidateExeption_Exception {
        @NotNull final TaskDTO task = getTask();
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        @NotNull final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
        taskEndpoint.persistTask(locator.getSession(), task);
    }

    @NotNull
    private TaskDTO getTask() throws SessionValidateExeption_Exception {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final String name = consoleService.getStringFieldFromConsole("Task name");
        @NotNull final String description = consoleService.getStringFieldFromConsole("Description");
        @NotNull final Date startDate = consoleService.getDateFieldFromConsole("Start date");
        @NotNull final Date endDate = consoleService.getDateFieldFromConsole("End date");
        @NotNull final String projectId = getProjectIdFromConsole();
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (endDate != null) task.setEndDate(endDate);
        task.setProjectId(projectId);
        task.setUserId(locator.getSession().getUserId());
        return task;
    }

    @NotNull
    private String getProjectIdFromConsole() throws SessionValidateExeption_Exception {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        String id;
        boolean flag;
        do {
            System.out.println("*** Please write project id ***");
            @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
            @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
            @NotNull final List<ProjectDTO> projectDTOS = projectEndpoint.findAllProjects(locator.getSession());
            projectDTOS.forEach(System.out::println);
            flag = true;
            id = consoleService.getNextLine();
            if (id == null || id.isEmpty()) {
                System.out.println("*** Wrong id ***");
                flag = false;
            }
        } while (!flag);
        return id;
    }

}
