package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.model.dto.TaskDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskRemoveCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_REMOVE;

    public TaskRemoveCommand(@NotNull final Bootstrap bootstrap) {
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
        System.out.println("*** Please enter id ***");
        @NotNull final List<TaskDTO> tasks = bootstrap.getTaskService().findAll();
        tasks.forEach(System.out::println);
        if (!bootstrap.getTaskService().remove(bootstrap.getScanner().nextLine())) {
            System.out.println("*** Wrong id ***");
        }
    }

}
