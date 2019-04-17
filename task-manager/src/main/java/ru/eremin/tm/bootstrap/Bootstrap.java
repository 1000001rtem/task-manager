package ru.eremin.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.exeption.BadCommandException;
import ru.eremin.tm.exeption.IncorrectCommandClassException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.model.entity.session.Session;
import ru.eremin.tm.model.repository.ProjectRepository;
import ru.eremin.tm.model.repository.TaskRepository;
import ru.eremin.tm.model.repository.UserRepository;
import ru.eremin.tm.model.repository.api.IProjectRepository;
import ru.eremin.tm.model.repository.api.ITaskRepository;
import ru.eremin.tm.model.repository.api.IUserRepository;
import ru.eremin.tm.model.service.ConsoleService;
import ru.eremin.tm.model.service.ProjectService;
import ru.eremin.tm.model.service.TaskService;
import ru.eremin.tm.model.service.UserService;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;
import ru.eremin.tm.model.service.api.IUserService;
import ru.eremin.tm.security.AuthService;
import ru.eremin.tm.security.IAuthService;
import ru.eremin.tm.security.IRegistrationService;
import ru.eremin.tm.security.RegistrationService;
import ru.eremin.tm.utils.Utils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class Bootstrap implements ServiceLocator {

    @NotNull
    @Getter
    private final IProjectService projectService;

    @NotNull
    @Getter
    private final ITaskService taskService;

    @NotNull
    @Getter
    private final IUserService userService;

    @NotNull
    @Getter
    private final IAuthService authService;

    @NotNull
    @Getter
    private final IRegistrationService registrationService;

    @NotNull
    private final Scanner scanner;

    @Nullable
    @Getter
    @Setter
    private Session session;

    @Nullable
    @Getter
    private ConsoleService consoleService;

    @NotNull
    @Getter
    private final Map<String, AbstractTerminalCommand> commands;

    public Bootstrap() {
        this.scanner = new Scanner(System.in);
        this.consoleService = new ConsoleService(this.scanner);
        @NotNull final IProjectRepository projectRepository = new ProjectRepository();
        @NotNull final ITaskRepository taskRepository = new TaskRepository();
        @NotNull final IUserRepository userRepository = new UserRepository();
        this.taskService = new TaskService(taskRepository);
        this.projectService = new ProjectService(projectRepository, this.taskService);
        this.userService = new UserService(userRepository);
        this.authService = new AuthService(userService);
        this.registrationService = new RegistrationService(userService);
        this.commands = new HashMap<>();
    }

    public void init(@NotNull final Class[] classes) {
        for (final Class commandClass : classes) {
            AbstractTerminalCommand command = null;
            try {
                command = initCommand(commandClass);
            } catch (IncorrectCommandClassException e) {
                e.printStackTrace();
            }
            command.setLocator(this);
            command.setSecured();
            commands.put(command.getName(), command);
        }
        initUsers();
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        while (true) {
            AbstractTerminalCommand command;
            final String answer = consoleService.getNextLine();
            if (answer == null) command = commands.get("help");
            else command = commands.get(answer);
            if (command == null) command = commands.get("help");
            if (command.isSecured() && session == null) System.out.println("*** Please log in ***");
            else {
                try {
                    command.execute();
                } catch (BadCommandException | IncorrectDataException | ClassNotFoundException | JAXBException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    File file = new File("data.ser");
                    file.delete();
                }
            }
        }
    }

    private void initUsers() {
        @NotNull final UserDTO user = new UserDTO();
        user.setId("6bf0f091-e795-42d1-bb9a-77799cdf37da");
        user.setLogin("user");
        user.setHashPassword(Utils.getHash("pass"));
        user.setRole(Role.USER);

        @NotNull final UserDTO admin = new UserDTO();
        admin.setId("6706e691-2f78-45ad-b021-3730c48959f0");
        admin.setLogin("admin");
        admin.setHashPassword(Utils.getHash("pass"));
        admin.setRole(Role.ADMIN);

        userService.persist(user);
        userService.persist(admin);
    }

    private AbstractTerminalCommand initCommand(@NotNull final Class<AbstractTerminalCommand> commandClass)
            throws IncorrectCommandClassException {
        if (!commandClass.getSuperclass().equals(AbstractTerminalCommand.class)) {
            throw new IncorrectCommandClassException("command super class is not AbstractTerminalCommand");
        }
        try {
            @NotNull final AbstractTerminalCommand command = commandClass.newInstance();
            return command;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IncorrectCommandClassException(e);
        }
    }

}
