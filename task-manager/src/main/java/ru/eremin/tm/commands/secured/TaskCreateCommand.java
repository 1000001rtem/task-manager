package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;

import java.util.Date;
import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskCreateCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_CREATE;

    @Override
    public String getName() {
        return command.getName();
    }

    @Override
    public String getDescription() {
        return command.getDescription();
    }

    @Override
    public void execute() {
        @NotNull final TaskDTO task = getTask();
        locator.getTaskService().persist(task);
        System.out.println("*** Task created: " + task + " ***");
    }

    @NotNull
    private TaskDTO getTask() {
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
        task.setUserId(locator.getSession().getUser().getId());
        return task;
    }

    @NotNull
    private String getProjectIdFromConsole() {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        String id;
        boolean flag;
        do {
            System.out.println("*** Please write project id ***");
            @NotNull final UserDTO userDTO = locator.getSession().getUser();
            @NotNull final List<ProjectDTO> projects = locator.getProjectService().findAll(userDTO.getId());
            projects.forEach(System.out::println);
            flag = true;
            id = consoleService.getNextLine();
            if (id == null || id.isEmpty() || locator.getProjectService().isExist(id)) {
                System.out.println("*** Wrong id ***");
                flag = false;
            }
        } while (!flag);
        return id;
    }

}
