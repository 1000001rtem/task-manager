package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.*;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserInfoCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "user_info";
    }

    @Override
    public String getDescription() {
        return "Show profile information";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final UserEndpointService endpointService = new UserEndpointService();
        @NotNull final UserEndpoint userEndpoint = endpointService.getUserEndpointPort();
        @NotNull final UserDTO userDTO = userEndpoint.findOneUser(locator.getSession(), locator.getSession().getUserId());
        System.out.println(userDTO.toString());
    }

}
