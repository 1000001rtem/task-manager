package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class LogoutCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "Logout";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() {
        locator.setSession(null);
        System.out.println("*** Session stop ***");
    }

}
