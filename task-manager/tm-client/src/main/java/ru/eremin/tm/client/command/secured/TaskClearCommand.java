package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class TaskClearCommand extends AbstractTerminalCommand {

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
    public void execute() {
        //locator.getTaskService().removeAll(locator.getSession().getUser().getId());
    }

}
