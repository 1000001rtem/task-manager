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
public class UserInfoCommand implements ICommand {

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    private ServiceLocator locator;

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
        @NotNull final UserDTO userDTO = userEndpoint.findOneUser(locator.getSession(), locator.getSession().getUserId());
        System.out.println(userDTO.toString());
    }

}
