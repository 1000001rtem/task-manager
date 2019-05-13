package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;

/**
 * @autor av.eremin on 10.04.2019.
 */

@Component
@NoArgsConstructor
public class ExitCommand implements ICommand {

    @Autowired
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
