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
public class TaskSortByStatusCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_sort_by_status";
    }

    @Override
    public String getDescription() {
        return "Tasks by status";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final TaskEndpointService taskEndpointService = new TaskEndpointService();
        @NotNull final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();
        @NotNull final List<TaskDTO> taskDTOList = taskEndpoint.findAllTasksSortedByStatus(locator.getSession());
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
                ", project='" + taskDTO.getProjectId() + '\'' +
                ", user='" + taskDTO.getUserId() + '\'' +
                ", createDate='" + taskDTO.getCreateDate() + '\'' +
                '}';
    }

}
