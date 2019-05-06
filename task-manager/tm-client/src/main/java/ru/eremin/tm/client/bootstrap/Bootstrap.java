package ru.eremin.tm.client.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.SessionDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final Map<String, ICommand> commands;

    @Getter
    @Setter
    @Nullable
    private SessionDTO session;

    @Inject
    @Getter
    @Nullable
    private ConsoleService consoleService;

    public Bootstrap() {
        this.commands = new HashMap<>();
    }

    public void init() {
        initCommands();
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        while (true) {
            ICommand command;
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

    private void initCommands() {
        final Instance<ICommand> abstractTerminalCommands = CDI.current().select(ICommand.class);
        for (final ICommand iCommand : abstractTerminalCommands) {
            commands.put(iCommand.getName(), iCommand);
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
