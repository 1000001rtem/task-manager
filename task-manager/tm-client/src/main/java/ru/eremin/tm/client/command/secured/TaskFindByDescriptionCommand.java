package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

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
    public void execute() {
//        @NotNull final ConsoleService consoleService = locator.getConsoleService();
//        @NotNull final ITaskService taskService = locator.getTaskService();
//        @NotNull final List<TaskDTO> tasks = taskService.findAll(locator.getSession().getUser().getId());
//        @NotNull final String key = consoleService
//                .getStringFieldFromConsole("*** Write description or part of description of task ***");
//        tasks.stream().filter(e -> e.getDescription().contains(key)).forEach(System.out::println);
    }

}
