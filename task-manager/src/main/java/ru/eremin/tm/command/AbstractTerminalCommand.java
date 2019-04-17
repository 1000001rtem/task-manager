package ru.eremin.tm.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.exeption.BadCommandException;
import ru.eremin.tm.exeption.IncorrectDataException;

import java.io.IOException;


/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public abstract class AbstractTerminalCommand {

    @Getter
    protected boolean isSecured;

    protected ServiceLocator locator;

    public abstract String getName();

    public abstract String getDescription();

    public abstract void setSecured();

    public abstract void execute() throws BadCommandException, IncorrectDataException, IOException, ClassNotFoundException;

    public void setLocator(@Nullable final ServiceLocator serviceLocator) {
        if (serviceLocator == null) throw new NullPointerException("ServiceLocator == null");
        this.locator = serviceLocator;
    }

}
