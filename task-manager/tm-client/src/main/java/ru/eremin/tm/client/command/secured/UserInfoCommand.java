package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class UserInfoCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "user_info";
    }

    @Override
    public String getDescription() {
        return "Show profile information";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() {
        //System.out.println(locator.getSession().getUser());
    }

}
