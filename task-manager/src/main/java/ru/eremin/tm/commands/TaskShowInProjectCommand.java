package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskShowInProjectCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_IN_PROJECT;

    public TaskShowInProjectCommand(@NotNull final Bootstrap bootstrap) {
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
        System.out.println("*** Please enter project id ***");
        @NotNull final List<ProjectDTO> projects = bootstrap.getProjectService().findAll();
        projects.forEach(System.out::println);
        @NotNull final List<TaskDTO> tasks = bootstrap.getTaskService().findByProjectId(bootstrap.getScanner().nextLine());
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found");
            return;
        }
        tasks.forEach(System.out::println);
    }

}
