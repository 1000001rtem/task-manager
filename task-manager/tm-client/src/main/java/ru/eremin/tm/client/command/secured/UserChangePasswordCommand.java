package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.UserEndpoint;
import ru.eremin.tm.server.endpoint.UserEndpointService;

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
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final String oldPassword = consoleService.getStringFieldFromConsole("Old password");
        @NotNull final String newPassword = consoleService.getStringFieldFromConsole("New Password");
        @NotNull final UserEndpointService userEndpointService = new UserEndpointService();
        @NotNull final UserEndpoint userEndpoint = userEndpointService.getUserEndpointPort();
        userEndpoint.changeUserPassword(locator.getSession(), oldPassword, newPassword);
    }

}
