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

/**
 * @autor av.eremin on 10.04.2019.
 */

@Component
@NoArgsConstructor
public class ProjectInfoCommand implements ICommand {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ConsoleService consoleService;

    @Override
    public String getName() {
        return "project_info";
    }

    @Override
    public String getDescription() {
        return "Show project information";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String id = consoleService.getStringFieldFromConsole("id");
        @NotNull final ProjectDTO projectDTO = projectEndpoint.findOneProject(locator.getSession(), id);
        if (projectDTO == null) System.out.println("Wrong id");
        else System.out.println(info(projectDTO));
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
