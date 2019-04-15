package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class TaskInfoCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_info";
    }

    @Override
    public String getDescription() {
        return "Show task information";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() {
        @NotNull final ConsoleService consoleService = locator.getConsoleService();
        System.out.println("*** Please enter id ***");
        @NotNull final UserDTO userDTO = locator.getSession().getUser();
        @NotNull final List<TaskDTO> tasks = locator.getTaskService().findAll(userDTO.getId());
        tasks.forEach(System.out::println);
        @Nullable final TaskDTO task = locator.getTaskService().findOne(consoleService.getNextLine());
        if (task == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(task.info());
    }

}
