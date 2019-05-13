package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.client.util.PasswordHashUtil;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.Role;
import ru.eremin.tm.server.endpoint.UserDTO;
import ru.eremin.tm.server.endpoint.UserEndpoint;


/**
 * @autor av.eremin on 12.04.2019.
 */

@Component
@NoArgsConstructor
public class UserRegistrationCommand implements ICommand {

    @Autowired
    private UserEndpoint userEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ConsoleService consoleService;

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
        @NotNull final String login = consoleService.getStringFieldFromConsole("Login");
        @NotNull final String hashPassword = PasswordHashUtil.md5(consoleService.getStringFieldFromConsole("Password"));
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setHashPassword(hashPassword);
        userDTO.setRole(Role.USER);
        userEndpoint.persistUser(userDTO);
    }

}
