package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.TaskDTO;
import ru.eremin.tm.server.endpoint.TaskEndpoint;

import java.util.List;

/**
 * @autor av.eremin on 16.04.2019.
 */

@Component
@NoArgsConstructor
public class TaskSortByEndDateCommand implements ICommand {

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Override
    public String getName() {
        return "task_sort_by_end";
    }

    @Override
    public String getDescription() {
        return "Tasks by end date";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final List<TaskDTO> taskDTOList = taskEndpoint.findAllTasksSortedByEndDate(locator.getSession());
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
