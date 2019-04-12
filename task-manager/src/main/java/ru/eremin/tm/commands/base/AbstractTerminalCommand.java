package ru.eremin.tm.commands.base;

import lombok.Getter;
import ru.eremin.tm.bootstrap.ServiceLocator;


/**
 * @autor av.eremin on 10.04.2019.
 */

public abstract class AbstractTerminalCommand {

    @Getter
    protected boolean isSecured = true;

    protected ServiceLocator locator;

    public AbstractTerminalCommand(final ServiceLocator locator) {
        this.locator = locator;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();

}
