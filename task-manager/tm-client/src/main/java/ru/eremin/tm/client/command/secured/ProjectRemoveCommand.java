package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;

/**
 * @autor av.eremin on 10.04.2019.
 */

@Component
@NoArgsConstructor
public class ProjectRemoveCommand implements ICommand {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ConsoleService consoleService;

    @Override
    public String getName() {
        return "project_remove";
    }

    @Override
    public String getDescription() {
        return "Remove selected project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String id = consoleService.getStringFieldFromConsole("id");
        projectEndpoint.removeProject(locator.getSession(), id);
    }

}
