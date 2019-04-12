package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskClearCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_CLEAR;

    public TaskClearCommand(@NotNull final Bootstrap bootstrap) {
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
        bootstrap.getTaskService().removeAll(bootstrap.getSession().getUser().getId());
    }

}
