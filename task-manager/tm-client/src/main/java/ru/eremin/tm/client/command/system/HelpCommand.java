package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@Component
@NoArgsConstructor
public class HelpCommand implements ICommand {

    @Autowired
    private ServiceLocator locator;

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Show all command";
    }

    @Override
    public boolean getSecured() {
        return false;
    }

    @Override
    public void execute() {
        final List<ICommand> commands = new ArrayList<>(locator.getCommands().values());
        commands.sort(Comparator.comparing(ICommand::getName));
        for (final ICommand command : commands) {
            System.out.println(command.getName() + ": " + command.getDescription());
        }
    }

}
