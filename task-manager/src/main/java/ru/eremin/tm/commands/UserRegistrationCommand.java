package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.utils.Utils;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserRegistrationCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.USER_REGISTRATION;

    public UserRegistrationCommand() {
        this.isSecured = false;
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
