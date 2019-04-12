package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.UserDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class ProjectRemoveCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.PROJECT_REMOVE;

    public ProjectRemoveCommand(final @NotNull ServiceLocator locator) {
        super(locator);
    }

    @Override
    public String getName() {
        return command.getName();
    }

    @Override
    public String getDescription() {
        return command.getDescription();
    }

    @Override
    public void execute() {
        System.out.println("*** Please enter id ***");
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        @NotNull final List<ProjectDTO> projects = locator.getProjectService().findByUserId(userDTO.getId());
        projects.forEach(System.out::println);
        if (!locator.getProjectService().remove(locator.getScanner().nextLine())) {
            System.out.println("*** Wrong Id ***");
        }
    }

}
