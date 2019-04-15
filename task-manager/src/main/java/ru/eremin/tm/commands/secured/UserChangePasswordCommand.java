package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.utils.Utils;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserChangePasswordCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.USER_CHANGE_PASSWORD;

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
        @NotNull final String oldPassword = Utils.getHash(consoleService.getStringFieldFromConsole("Old Password"));
        if (!oldPassword.equals(locator.getSession().getUser().getHashPassword())) {
            System.out.println("*** Wrong Password ***");
            return;
        }
        @NotNull final String newPassword = Utils.getHash(consoleService.getStringFieldFromConsole("New Password"));
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        userDTO.setHashPassword(newPassword);
        locator.getUserService().update(userDTO);
        locator.getSession().setUser(userDTO);
        System.out.println("*** Complete ***");
    }

}