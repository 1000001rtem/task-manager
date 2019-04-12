package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.ProjectDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class ProjectRemoveCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.PROJECT_REMOVE;

    public ProjectRemoveCommand(final @NotNull Bootstrap bootstrap) {
        super(bootstrap);
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
        @NotNull final List<ProjectDTO> projects = bootstrap.getProjectService().findAll();
        projects.forEach(System.out::println);
        if (!bootstrap.getProjectService().remove(bootstrap.getScanner().nextLine())) {
            System.out.println("*** Wrong Id ***");
        }
    }

}
