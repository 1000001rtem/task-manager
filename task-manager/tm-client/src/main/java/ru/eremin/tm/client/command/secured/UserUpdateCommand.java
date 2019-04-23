package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.*;

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
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String newLogin = locator.getConsoleService().getStringFieldFromConsole("New Login");
        @NotNull final UserEndpointService userEndpointService = new UserEndpointService();
        @NotNull final UserEndpoint userEndpoint = userEndpointService.getUserEndpointPort();
        @NotNull final UserDTO userDTO = userEndpoint.findOneUser(locator.getSession(), locator.getSession().getUserId());
        userDTO.setLogin(newLogin);
        userEndpoint.updateUser(locator.getSession(), userDTO);
    }

}
