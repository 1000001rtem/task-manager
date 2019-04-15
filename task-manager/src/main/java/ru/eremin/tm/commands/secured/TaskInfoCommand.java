package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskInfoCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_INFO;

    @Override
    public String getName() {
        return command.getName();
    }

    @Override
    public String getDescription() {
        return command.getDescription();
    }

    @Override
    public void execute() {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        System.out.println("*** Please enter id ***");
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        @NotNull final List<TaskDTO> tasks = locator.getTaskService().findByUserId(userDTO.getId());
        tasks.forEach(System.out::println);
        @Nullable final TaskDTO task = locator.getTaskService().findOne(consoleService.getNextLine());
        if (task == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(task.info());
    }

}
