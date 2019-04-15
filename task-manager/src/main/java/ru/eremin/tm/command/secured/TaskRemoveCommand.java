package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.ConsoleService;

import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class TaskRemoveCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_remove";
    }

    @Override
    public String getDescription() {
        return "Remove selected task";
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
        if (!locator.getTaskService().remove(consoleService.getNextLine())) {
            System.out.println("*** Wrong id ***");
        }
    }

}
