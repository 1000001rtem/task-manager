package ru.eremin.tm.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.entity.session.Session;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.utils.Utils;

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
    public void setSecured() {
        this.isSecured = false;
    }

    @Override
    public void execute() {
        System.out.println("*** AUTHORIZATION ***");
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final String login = consoleService.getStringFieldFromConsole("Login");
        @NotNull final String hashPassword = Utils.getHash(consoleService.getStringFieldFromConsole("Password"));
        @Nullable final Session session = locator.getAuthService().login(login, hashPassword);
        if (session == null) System.out.println("*** Wrong login or password ***");
        else locator.setSession(session);
    }

}
