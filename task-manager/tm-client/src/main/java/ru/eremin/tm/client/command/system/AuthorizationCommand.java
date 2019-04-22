package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.exeption.BadCommandException;
import ru.eremin.tm.server.endpoint.AuthorizationEndpoint;
import ru.eremin.tm.server.endpoint.AuthorizationEndpointService;
import ru.eremin.tm.server.endpoint.SessionDTO;


/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class AuthorizationCommand extends AbstractTerminalCommand {

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
    public void execute() throws BadCommandException {
        System.out.println("*** AUTHORIZATION ***");
        @NotNull final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        @NotNull final AuthorizationEndpoint authorizationEndpoint = authorizationEndpointService.getAuthorizationEndpointPort();
        @NotNull final String login = locator.getConsoleService().getStringFieldFromConsole("login");
        @NotNull final String password = locator.getConsoleService().getStringFieldFromConsole("password");
        @NotNull final SessionDTO session = authorizationEndpoint.login(login, password);
        if (session == null) System.out.println("Wrong login or password");
        else locator.setSession(session);
    }

}
