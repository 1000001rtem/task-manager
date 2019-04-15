package ru.eremin.tm.commands.secured;

import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;

/**
 * @autor av.eremin on 11.04.2019.
 */

public class DataBeanCleanCommand extends AbstractTerminalCommand {

    private static final CommandEnum command = CommandEnum.DATA_BEAN_CLEAN;

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
