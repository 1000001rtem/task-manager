package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.UserDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ProjectShowAllCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_list";
    }

    @Override
    public String getDescription() {
        return "Show all Projects";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() {
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        @NotNull final List<ProjectDTO> projects = locator.getProjectService().findAll(userDTO.getId());
        projects.forEach(System.out::println);
    }

}
