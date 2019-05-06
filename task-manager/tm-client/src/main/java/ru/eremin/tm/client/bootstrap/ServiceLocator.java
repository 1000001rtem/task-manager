package ru.eremin.tm.client.bootstrap;

import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.SessionDTO;

import java.util.Map;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface ServiceLocator {

    void init();

    SessionDTO getSession();

    void setSession(SessionDTO session);

    ConsoleService getConsoleService();

    Map<String, ICommand> getCommands();

    void closeSession();

}
