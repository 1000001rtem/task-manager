package ru.eremin.tm.client.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.client.exeption.IncorrectCommandClassException;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.SessionDTO;

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
    private final Scanner scanner;

    @Getter
    @NotNull
    private final Map<String, AbstractTerminalCommand> commands;

    @Getter
    @Setter
    @Nullable
    private SessionDTO session;

    @Getter
    @Nullable
    private ConsoleService consoleService;

    public Bootstrap() {
        this.scanner = new Scanner(System.in);
        this.consoleService = new ConsoleService(this.scanner);
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
            command.getSecured();
            commands.put(command.getName(), command);
        }
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        while (true) {
            AbstractTerminalCommand command;
            final String answer = consoleService.getNextLine();
            if (answer == null) command = commands.get("help");
            else command = commands.get(answer);
            if (command == null) command = commands.get("help");
            if (command.getSecured() && session == null) System.out.println("*** Please log in ***");
            else {
                try {
                    command.execute();
                } catch (ClassNotFoundException | JAXBException | IncorrectDataException_Exception e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    File file = new File("data.ser");
                    file.delete();
                } catch (AccessForbiddenException_Exception e) {
                    e.printStackTrace();
                }
            }
        }
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

    public void closeSession() {
        try {
            commands.get("logout").execute();
            session = null;
        } catch (IOException | ClassNotFoundException | JAXBException | IncorrectDataException_Exception | AccessForbiddenException_Exception e) {
            e.printStackTrace();
        }
    }

}
