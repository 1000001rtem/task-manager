package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserUpdateCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.USER_UPDATE;

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
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final String newLogin = consoleService.getStringFieldFromConsole("New Login");
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        userDTO.setLogin(newLogin);
        locator.getUserService().update(userDTO);
        locator.getSession().setUser(userDTO);
    }

}
