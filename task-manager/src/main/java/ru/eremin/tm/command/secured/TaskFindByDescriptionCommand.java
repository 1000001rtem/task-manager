package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.model.service.api.ITaskService;

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
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        @NotNull final List<TaskDTO> tasks = taskService.findAll(locator.getSession().getUser().getId());
        @NotNull final String key = consoleService
                .getStringFieldFromConsole("*** Write description or part of description of task ***");
        tasks.stream().filter(e -> e.getDescription().contains(key)).forEach(System.out::println);
    }

}
