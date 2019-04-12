package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserInfoCommand extends AbstractTerminalCommand {

    @NotNull
    private final static CommandEnum command = CommandEnum.USER_INFO;

    public UserInfoCommand(@NotNull final Bootstrap bootstrap) {
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
        System.out.println(bootstrap.getSession().getUser());
    }

}
