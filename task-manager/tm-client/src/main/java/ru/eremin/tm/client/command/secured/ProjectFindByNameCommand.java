package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class ProjectFindByNameCommand implements ICommand {

    @Inject
    private ProjectEndpoint projectEndpoint;

    @Inject
    private ServiceLocator locator;

    @Inject
    private ConsoleService consoleService;

    @Override
    public String getName() {
        return "project_find_by_name";
    }

    @Override
    public String getDescription() {
        return "Find project by name";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String name
                = consoleService.getStringFieldFromConsole("name or part of name of project");
        @NotNull final List<ProjectDTO> projectDTOList = projectEndpoint.findProjectsByName(locator.getSession(), name);
        projectDTOList.forEach(this::print);
    }

    private void print(@NotNull final ProjectDTO projectDTO) {
        System.out.println(info(projectDTO));
    }

    private String info(@NotNull final ProjectDTO projectDTO) {
        return projectDTO.getName() +
                "{id='" + projectDTO.getId() + '\'' +
                ", description='" + projectDTO.getDescription() + '\'' +
                ", status='" + projectDTO.getStatus() + '\'' +
                ", startDate=" + projectDTO.getStartDate() +
                ", endDate=" + projectDTO.getEndDate() +
                ", user=" + projectDTO.getUserId() +
                ", createDate=" + projectDTO.getCreateDate() +
                '}';
    }

}
