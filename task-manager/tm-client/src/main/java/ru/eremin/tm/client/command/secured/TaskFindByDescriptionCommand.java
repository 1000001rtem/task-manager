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
public class TaskFindByDescriptionCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_find_by_description";
    }

    @Override
    public String getDescription() {
        return "Find task by description";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String description
                = locator.getConsoleService().getStringFieldFromConsole("description or part of description of task");
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        @NotNull final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
        @NotNull final List<TaskDTO> taskDTOList = taskEndpoint.findTasksByDescription(locator.getSession(), description);
        taskDTOList.forEach(this::print);
    }

    private void print(@NotNull final TaskDTO taskDTO) {
        System.out.println(info(taskDTO));
    }

    private String info(@NotNull final TaskDTO taskDTO) {
        return taskDTO.getName() +
                "{id='" + taskDTO.getId() + '\'' +
                ", name='" + taskDTO.getName() + '\'' +
                ", description='" + taskDTO.getDescription() + '\'' +
                ", status='" + taskDTO.getStatus() + '\'' +
                ", startDate=" + taskDTO.getStartDate() +
                ", endDate=" + taskDTO.getEndDate() +
                ", projectId='" + taskDTO.getProjectId() + '\'' +
                ", userId='" + taskDTO.getUserId() + '\'' +
                ", createDate='" + taskDTO.getCreateDate() + '\'' +
                '}';
    }

}
