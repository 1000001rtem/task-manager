package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.*;

import javax.inject.Inject;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserChangePasswordCommand implements ICommand {

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    private ServiceLocator locator;

    @Inject
    private ConsoleService consoleService;

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
        @NotNull final String oldPassword = consoleService.getStringFieldFromConsole("Old password");
        @NotNull final String newPassword = consoleService.getStringFieldFromConsole("New Password");
        userEndpoint.changeUserPassword(locator.getSession(), oldPassword, newPassword);
    }

}
