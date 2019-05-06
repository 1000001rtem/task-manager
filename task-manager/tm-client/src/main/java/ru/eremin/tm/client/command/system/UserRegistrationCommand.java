package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.client.util.PasswordHashUtil;
import ru.eremin.tm.server.endpoint.*;


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
    public boolean getSecured() {
        return false;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception {
        System.out.println("*** REGISTRATION ***");
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final String login = consoleService.getStringFieldFromConsole("Login");
        @NotNull final String hashPassword = PasswordHashUtil.md5(consoleService.getStringFieldFromConsole("Password"));
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setHashPassword(hashPassword);
        userDTO.setRole(Role.USER);
        @NotNull final UserEndpointService userEndpointService = new UserEndpointService();
        @NotNull final UserEndpoint userEndpoint = userEndpointService.getUserEndpointPort();
        userEndpoint.persistUser(userDTO);
    }

}
