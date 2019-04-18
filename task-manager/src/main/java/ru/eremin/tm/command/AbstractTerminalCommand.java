package ru.eremin.tm.command;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.exeption.BadCommandException;
import ru.eremin.tm.exeption.IncorrectDataException;

import javax.xml.bind.JAXBException;
import java.io.IOException;


/**
 * @autor av.eremin on 10.04.2019.
 */

@NoArgsConstructor
public abstract class AbstractTerminalCommand {

    protected ServiceLocator locator;

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean getSecured();

    public abstract void execute() throws BadCommandException, IncorrectDataException, IOException, ClassNotFoundException, JAXBException;

    public void setLocator(@Nullable final ServiceLocator serviceLocator) {
        if (serviceLocator == null) throw new NullPointerException("ServiceLocator == null");
        this.locator = serviceLocator;
    }

}
