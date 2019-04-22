package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;


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
    public void execute() {
//        System.out.println("*** REGISTRATION ***");
//        @NotNull final ConsoleService consoleService = locator.getConsoleService();
//        @NotNull final String login = consoleService.getStringFieldFromConsole("Login");
//        @NotNull final String hashPassword = Utils.getHash(consoleService.getStringFieldFromConsole("Password"));
//        @NotNull final UserDTO userDTO = new UserDTO();
//        userDTO.setLogin(login);
//        userDTO.setHashPassword(hashPassword);
//        userDTO.setRole(Role.USER);
//        locator.getRegistrationService().registration(userDTO);
    }

}
