package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.utils.ConsoleHelper;

import java.util.Date;
import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskCreateCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_CREATE;

    public TaskCreateCommand(final @NotNull Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        bootstrap.getTaskService().persist(task);
        System.out.println("*** Task created: " + task + " ***");
    }

    @NotNull
    private TaskDTO getTask() {
        @NotNull final ConsoleHelper helper = new ConsoleHelper(bootstrap.getScanner());
        @NotNull final String name = helper.getStringFieldFromConsole("Task name");
        @NotNull final String description = helper.getStringFieldFromConsole("Description");
        @NotNull final Date startDate = helper.getDateFieldFromConsole("Start date");
        @NotNull final Date endDate = helper.getDateFieldFromConsole("End date");
        @NotNull final String projectId = getProjectIdFromConsole();
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (endDate != null) task.setEndDate(endDate);
        task.setProjectId(projectId);
        task.setUserId(bootstrap.getSession().getUser().getId());
        return task;
    }

    @NotNull
    private String getProjectIdFromConsole() {
        String id;
        boolean flag;
        do {
            System.out.println("*** Please write project id ***");
            @NotNull final UserDTO userDTO = bootstrap.getSession().getUser();
            @NotNull final List<ProjectDTO> projects = bootstrap.getProjectService().findByUserId(userDTO.getId());
            projects.forEach(System.out::println);
            flag = true;
            id = bootstrap.getScanner().nextLine();
            if (id == null || id.isEmpty() || bootstrap.getProjectService().isExist(id)) {
                System.out.println("*** Wrong id ***");
                flag = false;
            }
        } while (!flag);
        return id;
    }

}
