package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class LogoutCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.LOGOUT;

    public LogoutCommand(@NotNull final Bootstrap bootstrap) {
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
        bootstrap.setSession(null);
        System.out.println("*** Session stop ***");
    }

}
