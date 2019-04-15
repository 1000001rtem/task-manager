package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class ProjectInfoCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.PROJECT_INFO;

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
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        System.out.println("*** Please enter id ***");
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        @NotNull final List<ProjectDTO> projects = locator.getProjectService().findAll(userDTO.getId());
        projects.forEach(System.out::println);
        @NotNull final ProjectDTO project = locator.getProjectService().findOne(consoleService.getNextLine());
        if (project == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(project.info());
    }

}
