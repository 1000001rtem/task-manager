package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserInfoCommand extends AbstractTerminalCommand {

    @NotNull
    private final static CommandEnum command = CommandEnum.USER_INFO;

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
        System.out.println(locator.getSession().getUser());
    }

}
