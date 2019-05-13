package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.TaskEndpoint;

/**
 * @autor av.eremin on 10.04.2019.
 */

@Component
@NoArgsConstructor
public class TaskClearCommand implements ICommand {

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Override
    public String getName() {
        return "task_clear";
    }

    @Override
    public String getDescription() {
        return "Remove all task";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        taskEndpoint.findAllTasks(locator.getSession());
    }

}
