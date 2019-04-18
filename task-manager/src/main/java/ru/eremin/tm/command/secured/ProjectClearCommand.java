package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ProjectClearCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_clear";
    }

    @Override
    public String getDescription() {
        return "Remove all project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() {
        locator.getProjectService().removeAll(locator.getSession().getUser().getId());
    }

}
