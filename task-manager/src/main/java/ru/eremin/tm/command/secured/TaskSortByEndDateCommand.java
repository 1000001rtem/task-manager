package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.api.ITaskService;

import java.util.List;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class TaskSortByEndDateCommand extends AbstractTerminalCommand {

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
    public void execute() {
        @NotNull final ITaskService taskService = locator.getTaskService();
        @NotNull final String userId = locator.getSession().getUser().getId();
        @NotNull final List<TaskDTO> projects = taskService.findAllSortedByEndDate(userId);
        projects.forEach(System.out::println);
    }

}
