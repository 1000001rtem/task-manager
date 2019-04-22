package ru.eremin.tm.client.command;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.exeption.BadCommandException;
import ru.eremin.tm.client.exeption.IncorrectDataException;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;

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

    public abstract void execute() throws BadCommandException, IncorrectDataException, IOException, ClassNotFoundException, JAXBException, SessionValidateExeption_Exception, IncorrectDataException_Exception;

    public void setLocator(@Nullable final ServiceLocator serviceLocator) {
        if (serviceLocator == null) throw new NullPointerException("ServiceLocator == null");
        this.locator = serviceLocator;
    }

}
