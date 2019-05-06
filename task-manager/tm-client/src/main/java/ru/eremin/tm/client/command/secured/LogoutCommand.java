package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.AuthorizationEndpoint;
import ru.eremin.tm.server.endpoint.AuthorizationEndpointService;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;

import javax.inject.Inject;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class LogoutCommand implements ICommand {

    @Inject
    private ServiceLocator locator;

    @Inject
    private AuthorizationEndpoint authorizationEndpoint;

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
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        authorizationEndpoint.logout(locator.getSession());
        locator.setSession(null);
        System.out.println("*** Session stop ***");
    }

}
