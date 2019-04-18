package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.utils.Utils;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserChangePasswordCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "user_change_password";
    }

    @Override
    public String getDescription() {
        return "Change password";
    }

    @Override
    public boolean getSecured() {
        return true;
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
