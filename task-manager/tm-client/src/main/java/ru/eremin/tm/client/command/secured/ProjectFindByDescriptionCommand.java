package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.ProjectDTO;
import ru.eremin.tm.server.endpoint.ProjectEndpoint;

import java.util.List;

/**
 * @autor av.eremin on 16.04.2019.
 */

@Component
@NoArgsConstructor
public class ProjectFindByDescriptionCommand implements ICommand {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ConsoleService consoleService;

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
                consoleService.getStringFieldFromConsole("description or part of description of project");
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
                ", user=" + projectDTO.getUserId() +
                ", createDate=" + projectDTO.getCreateDate() +
                '}';
    }

}
