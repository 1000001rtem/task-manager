package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;

/**
 * @autor av.eremin on 11.04.2019.
 */

public class DataBeanCleanCommand extends AbstractTerminalCommand {

    private static final CommandEnum command = CommandEnum.DATA_BEAN_CLEAN;

    public DataBeanCleanCommand(final @NotNull Bootstrap bootstrap) {
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

    }

}
