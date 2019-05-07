package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.AuthorizationEndpoint;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.SessionDTO;

import javax.inject.Inject;


/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class AuthorizationCommand implements ICommand {

    @Inject
    private AuthorizationEndpoint authorizationEndpoint;

    @Inject
    private ServiceLocator locator;

    @Inject
    private ConsoleService consoleService;

    @Override
    public String getName() {
        return "auth";
    }

    @Override
    public String getDescription() {
        return "Authorization";
    }

    @Override
    public boolean getSecured() {
        return false;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        System.out.println("*** AUTHORIZATION ***");
        if (locator.getSession() != null) {
            System.out.println("*** Please logout ***");
            return;
        }
        @NotNull final String login = consoleService.getStringFieldFromConsole("login");
        @NotNull final String password = consoleService.getStringFieldFromConsole("password");
        @NotNull final SessionDTO session = authorizationEndpoint.login(login, password);
        if (session == null) System.out.println("Wrong login or password");
        else locator.setSession(session);
    }

}
