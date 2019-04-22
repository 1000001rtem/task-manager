package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.AuthorizationEndpoint;
import ru.eremin.tm.server.endpoint.AuthorizationEndpointService;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class LogoutCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "Logout";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws SessionValidateExeption_Exception, IncorrectDataException_Exception {
        @NotNull final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        @NotNull final AuthorizationEndpoint authorizationEndpoint = authorizationEndpointService.getAuthorizationEndpointPort();
        authorizationEndpoint.logout(locator.getSession());
        locator.setSession(null);
        System.out.println("*** Session stop ***");
    }

}
