package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.TaskDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskShowAllCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_LIST;

    public TaskShowAllCommand(final @NotNull Bootstrap bootstrap) {
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
        @NotNull final List<TaskDTO> tasks = bootstrap.getTaskService().findAll();
        tasks.forEach(System.out::println);
    }

}
