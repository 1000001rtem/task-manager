package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.utils.ConsoleHelper;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserUpdateCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.USER_UPDATE;

    public UserUpdateCommand(@NotNull final ServiceLocator locator) {
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
        @NotNull final ConsoleHelper helper = new ConsoleHelper(locator.getScanner());
        @NotNull final String newLogin = helper.getStringFieldFromConsole("New Login");
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        userDTO.setLogin(newLogin);
        locator.getUserService().update(userDTO);
        locator.getSession().setUser(userDTO);
    }

}
