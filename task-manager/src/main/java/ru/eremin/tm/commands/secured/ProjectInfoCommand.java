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

public class ProjectInfoCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.PROJECT_INFO;

    public ProjectInfoCommand(@NotNull final Bootstrap bootstrap) {
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
        @NotNull final ProjectDTO project = bootstrap.getProjectService().findOne(bootstrap.getScanner().nextLine());
        if (project == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(project.info());
    }

}
