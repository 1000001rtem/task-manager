package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.UserDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class ProjectShowAllCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.PROJECT_LIST;

    public ProjectShowAllCommand(@NotNull final Bootstrap bootstrap) {
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
        @NotNull final UserDTO userDTO = bootstrap.getSession().getUser();
        @NotNull final List<ProjectDTO> projects = bootstrap.getProjectService().findByUserId(userDTO.getId());
        projects.forEach(System.out::println);
    }
}
