package ru.eremin.tm.client.command;

import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;

import javax.xml.bind.JAXBException;
import java.io.IOException;


/**
 * @autor av.eremin on 10.04.2019.
 */


public interface ICommand {

    String getName();

    String getDescription();

    boolean getSecured();

    void execute() throws IOException, ClassNotFoundException, JAXBException, IncorrectDataException_Exception, AccessForbiddenException_Exception;

}
