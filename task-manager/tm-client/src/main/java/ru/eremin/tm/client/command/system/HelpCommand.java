package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class HelpCommand extends AbstractTerminalCommand {

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
        final List<AbstractTerminalCommand> commands = new ArrayList<>(locator.getCommands().values());
        commands.sort(Comparator.comparing(AbstractTerminalCommand::getName));
        for (final AbstractTerminalCommand command : commands) {
            System.out.println(command.getName() + ": " + command.getDescription());
        }
    }

}
