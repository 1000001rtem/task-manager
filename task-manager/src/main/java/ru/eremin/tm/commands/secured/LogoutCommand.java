package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class LogoutCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.LOGOUT;

    public LogoutCommand(@NotNull final ServiceLocator locator) {
        super(locator);
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
        locator.setSession(null);
        System.out.println("*** Session stop ***");
    }

}
