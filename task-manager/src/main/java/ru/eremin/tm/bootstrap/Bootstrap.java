package ru.eremin.tm.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.commands.*;
import ru.eremin.tm.model.repository.ProjectRepository;
import ru.eremin.tm.model.repository.TaskRepository;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.ProjectService;
import ru.eremin.tm.model.service.TaskService;
import ru.eremin.tm.utils.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
public class Bootstrap {

    @NotNull
    private final ProjectService projectService;

    @NotNull
    private final TaskService taskService;

    @NotNull
    private final Scanner scanner;

    @NotNull
    private final Map<String, AbstractTerminalCommand> commands;

    public Bootstrap() {
        this.scanner = new Scanner(System.in);
        @NotNull final ProjectRepository projectRepository = new ProjectRepository();
        @NotNull final TaskRepository taskRepository = new TaskRepository();
        this.taskService = new TaskService(taskRepository);
        this.projectService = new ProjectService(projectRepository, this.taskService);
        this.commands = new HashMap<>();
    }

    public void init() {
        initCommand();
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String answer;
        while (true) {
            answer = parseLine(scanner.nextLine());
            final AbstractTerminalCommand command = commands.get(answer);
            if (command == null) commands.get(CommandEnum.HELP.getName()).execute();
            else commands.get(answer).execute();
        }
    }

    private void initCommand() {
        final AbstractTerminalCommand helpCommand = new HelpCommand(this);
        final AbstractTerminalCommand exitCommand = new ExitCommand(this);
        final AbstractTerminalCommand projectCreateCommand = new ProjectCreateCommand(this);
        final AbstractTerminalCommand projectShowAllCommand = new ProjectShowAllCommand(this);
        final AbstractTerminalCommand projectInfoCommand = new ProjectInfoCommand(this);
        final AbstractTerminalCommand projectRemoveCommand = new ProjectRemoveCommand(this);
        final AbstractTerminalCommand projectClearCommand = new ProjectClearCommand(this);
        final AbstractTerminalCommand taskCreateCommand = new TaskCreateCommand(this);
        final AbstractTerminalCommand taskShowAllCommand = new TaskShowAllCommand(this);
        final AbstractTerminalCommand taskInfoCommand = new TaskInfoCommand(this);
        final AbstractTerminalCommand taskRemoveCommand = new TaskRemoveCommand(this);
        final AbstractTerminalCommand taskClearCommand = new TaskClearCommand(this);
        final AbstractTerminalCommand taskShowInProjectCommand = new TaskShowInProjectCommand(this);

        commands.put(helpCommand.getName(), helpCommand);
        commands.put(exitCommand.getName(), exitCommand);
        commands.put(projectCreateCommand.getName(), projectCreateCommand);
        commands.put(projectShowAllCommand.getName(), projectShowAllCommand);
        commands.put(projectInfoCommand.getName(), projectInfoCommand);
        commands.put(projectRemoveCommand.getName(), projectRemoveCommand);
        commands.put(projectClearCommand.getName(), projectClearCommand);
        commands.put(taskCreateCommand.getName(), taskCreateCommand);
        commands.put(taskShowAllCommand.getName(), taskShowAllCommand);
        commands.put(taskInfoCommand.getName(), taskInfoCommand);
        commands.put(taskRemoveCommand.getName(), taskRemoveCommand);
        commands.put(taskClearCommand.getName(), taskClearCommand);
        commands.put(taskShowInProjectCommand.getName(), taskShowInProjectCommand);
    }


    @Nullable
    private String parseLine(@Nullable final String nextLine) {
        if (nextLine == null || nextLine.isEmpty()) return null;
        if (nextLine.startsWith(CommandEnum.HELP.toString())) return CommandEnum.HELP.toString();
        if (nextLine.startsWith(CommandEnum.EXIT.toString())) return CommandEnum.EXIT.toString();
        if (nextLine.startsWith(CommandEnum.PROJECT_CREATE.toString())) return CommandEnum.PROJECT_CREATE.toString();
        if (nextLine.startsWith(CommandEnum.PROJECT_LIST.toString())) return CommandEnum.PROJECT_LIST.toString();
        if (nextLine.startsWith(CommandEnum.PROJECT_INFO.toString())) return CommandEnum.PROJECT_INFO.toString();
        if (nextLine.startsWith(CommandEnum.PROJECT_REMOVE.toString())) return CommandEnum.PROJECT_REMOVE.toString();
        if (nextLine.startsWith(CommandEnum.PROJECT_CLEAR.toString())) return CommandEnum.PROJECT_CLEAR.toString();
        if (nextLine.startsWith(CommandEnum.TASK_CREATE.toString())) return CommandEnum.TASK_CREATE.toString();
        if (nextLine.startsWith(CommandEnum.TASK_LIST.toString())) return CommandEnum.TASK_LIST.toString();
        if (nextLine.startsWith(CommandEnum.TASK_INFO.toString())) return CommandEnum.TASK_INFO.toString();
        if (nextLine.startsWith(CommandEnum.TASK_IN_PROJECT.toString())) return CommandEnum.TASK_IN_PROJECT.toString();
        if (nextLine.startsWith(CommandEnum.TASK_REMOVE.toString())) return CommandEnum.TASK_REMOVE.toString();
        if (nextLine.startsWith(CommandEnum.TASK_CLEAR.toString())) return CommandEnum.TASK_CLEAR.toString();
        return null;
    }

}
