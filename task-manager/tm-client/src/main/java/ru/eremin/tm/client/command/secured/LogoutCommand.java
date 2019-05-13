package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.AuthorizationEndpoint;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Component
@NoArgsConstructor
public class LogoutCommand implements ICommand {

    @Autowired
    private ServiceLocator locator;

    @Autowired
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
