package ru.eremin.tm.client.command;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;

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

    public abstract void execute() throws IOException, ClassNotFoundException, JAXBException, IncorrectDataException_Exception, AccessForbiddenException_Exception;

    public void setLocator(@Nullable final ServiceLocator serviceLocator) {
        if (serviceLocator == null) throw new NullPointerException("ServiceLocator == null");
        this.locator = serviceLocator;
    }

}
