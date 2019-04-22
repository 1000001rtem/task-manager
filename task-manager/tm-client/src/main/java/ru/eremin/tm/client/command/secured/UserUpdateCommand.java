package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserUpdateCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "user_update";
    }

    @Override
    public String getDescription() {
        return "Update profile data";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() {
//        @NotNull final ConsoleService consoleService = locator.getConsoleService();
//        @NotNull final String newLogin = consoleService.getStringFieldFromConsole("New Login");
//        @NotNull final UserDTO userDTO = locator.getSession().getUser();
//        userDTO.setLogin(newLogin);
//        locator.getUserService().update(userDTO);
//        locator.getSession().setUser(userDTO);
    }

}
