package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.*;

import java.util.List;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class ProjectFindByDescriptionCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "project_find_by_description";
    }

    @Override
    public String getDescription() {
        return "Find project by Description";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String description =
                locator.getConsoleService().getStringFieldFromConsole("description or part of description of project");
        @NotNull final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
        @NotNull final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();
        @NotNull final List<ProjectDTO> projectDTOList =
                projectEndpoint.findProjectsByDescription(locator.getSession(), description);
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
                ", userId=" + projectDTO.getUserId() +
                ", createDate=" + projectDTO.getCreateDate() +
                '}';
    }

}
