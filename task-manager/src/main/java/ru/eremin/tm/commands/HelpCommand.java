package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class HelpCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.HELP;

    public HelpCommand(@NotNull final Bootstrap bootstrap) {
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
        for (final AbstractTerminalCommand command : bootstrap.getCommands().values()) {
            System.out.println(command.getName() + ": " + command.getDescription());
        }
    }

}
