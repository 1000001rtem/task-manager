package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.UserDTO;
import ru.eremin.tm.server.endpoint.UserEndpoint;

/**
 * @autor av.eremin on 12.04.2019.
 */

@Component
@NoArgsConstructor
public class UserInfoCommand implements ICommand {

    @Autowired
    private UserEndpoint userEndpoint;

    @Autowired
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
