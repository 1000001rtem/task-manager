package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class ExitCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.EXIT;

    public ExitCommand() {
        this.isSecured = false;
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
        System.out.println("*** GOODBYE ***");
        System.exit(0);
    }

}
