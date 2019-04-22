package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.exeption.IncorrectDataException;

/**
 * @autor av.eremin on 16.04.2019.
 */

@NoArgsConstructor
public class TaskChangeStatusCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_change_status";
    }

    @Override
    public String getDescription() {
        return "Change status of task";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException {
//        @NotNull final ITaskService taskService = locator.getTaskService();
//        @NotNull final ConsoleService consoleService = locator.getConsoleService();
//        @NotNull final String projectId = consoleService.getStringFieldFromConsole("Task id");
//        @NotNull final TaskDTO taskDTO = taskService.findOne(projectId);
//        if (taskDTO == null) {
//            throw new IncorrectDataException("Wrong id");
//        }
//        @NotNull final List<Status> statuses = Arrays.stream(Status.values()).collect(Collectors.toList());
//        statuses.forEach(System.out::println);
//        @NotNull final String newStatus = consoleService.getStringFieldFromConsole("New status");
//        for (final Status status : statuses) {
//            if (status.getDisplayName().equalsIgnoreCase(newStatus)) taskDTO.setStatus(status);
//        }
//        if (!newStatus.equalsIgnoreCase(taskDTO.getStatus().getDisplayName())) {
//            throw new IncorrectDataException("Wrong status");
//        }
//        taskService.update(taskDTO);
    }

}
