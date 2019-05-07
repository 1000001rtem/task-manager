package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;

import javax.inject.Inject;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ExitCommand implements ICommand {

    @Inject
    private ServiceLocator locator;

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Exit";
    }

    @Override
    public boolean getSecured() {
        return false;
    }

    @Override
    public void execute() {
        locator.closeSession();
        System.out.println("*** GOODBYE ***");
        System.exit(0);
    }

}
