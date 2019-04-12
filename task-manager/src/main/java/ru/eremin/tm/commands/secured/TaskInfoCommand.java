package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class TaskInfoCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.TASK_INFO;

    public TaskInfoCommand(@NotNull final ServiceLocator locator) {
        super(locator);
    }

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
        System.out.println("*** Please enter id ***");
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        @NotNull final List<TaskDTO> tasks = locator.getTaskService().findByUserId(userDTO.getId());
        tasks.forEach(System.out::println);
        @Nullable final TaskDTO task = locator.getTaskService().findOne(locator.getScanner().nextLine());
        if (task == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(task.info());
    }

}
