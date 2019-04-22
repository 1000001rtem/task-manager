package ru.eremin.tm.client.command.system;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public class ExitCommand extends AbstractTerminalCommand {

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
        System.out.println("*** GOODBYE ***");
        System.exit(0);
    }

}
