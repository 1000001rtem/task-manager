package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 10.04.2019.
 */


@NoArgsConstructor
public class TaskShowInProjectCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "task_in_project";
    }

    @Override
    public String getDescription() {
        return "Show all tasks in selected project";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() {
//        @NotNull final ConsoleService consoleService = locator.getConsoleService();
//        System.out.println("*** Please enter project id ***");
//        @NotNull final UserDTO userDTO = locator.getSession().getUser();
//        @NotNull final List<ProjectDTO> projects = locator.getProjectService().findAll(userDTO.getId());
//        projects.forEach(System.out::println);
//        @NotNull final List<TaskDTO> tasks = locator.getTaskService().findByProjectId(consoleService.getNextLine());
//        if (tasks.isEmpty()) {
//            System.out.println("Tasks not found");
//            return;
//        }
//        tasks.forEach(System.out::println);
    }

}
