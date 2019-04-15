package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;

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
    public void setSecured() {
        this.isSecured = true;
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
