package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.Role;
import ru.eremin.tm.utils.ConsoleHelper;
import ru.eremin.tm.utils.Utils;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class UserRegistrationCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.USER_REGISTRATION;

    public UserRegistrationCommand(@NotNull final Bootstrap bootstrap) {
        super(bootstrap);
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
        @NotNull final ConsoleHelper helper = new ConsoleHelper(bootstrap.getScanner());
        @NotNull final String login = helper.getStringFieldFromConsole("Login");
        @NotNull final String hashPassword = Utils.getHash(helper.getStringFieldFromConsole("Password"));
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setHashPassword(hashPassword);
        userDTO.setRole(Role.USER);
        bootstrap.getRegistrationService().registration(userDTO);
    }

}
