package ru.eremin.tm.command.system;

import lombok.NoArgsConstructor;
import ru.eremin.tm.command.AbstractTerminalCommand;

import java.util.Collection;

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
    public void setSecured() {
        this.isSecured = false;
    }

    @Override
    public void execute() {
        final Collection<AbstractTerminalCommand> commands = locator.getCommands().values();
        for (final AbstractTerminalCommand command : commands) {
            System.out.println(command.getName() + ": " + command.getDescription());
        }
//        for (final CommandEnum command : CommandEnum.values()) {
//            System.out.println(command.getName() + ": " + command.getDescription());
//        }
    }

}
