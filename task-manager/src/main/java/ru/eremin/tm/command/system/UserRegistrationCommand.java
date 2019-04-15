package ru.eremin.tm.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.utils.Utils;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserRegistrationCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "user_registration";
    }

    @Override
    public String getDescription() {
        return "Registration new user";
    }

    @Override
    public void setSecured() {
        this.isSecured = false;
    }

    @Override
    public void execute() {
        System.out.println("*** REGISTRATION ***");
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final String login = consoleService.getStringFieldFromConsole("Login");
        @NotNull final String hashPassword = Utils.getHash(consoleService.getStringFieldFromConsole("Password"));
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setHashPassword(hashPassword);
        userDTO.setRole(Role.USER);
        locator.getRegistrationService().registration(userDTO);
    }

}
