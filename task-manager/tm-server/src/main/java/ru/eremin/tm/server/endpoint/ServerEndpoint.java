package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.IServerEndpoint;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ServerDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @autor av.eremin on 08.05.2019.
 */

@WebService
public class ServerEndpoint implements IServerEndpoint {

    @Inject
    @NotNull
    private ISessionService sessionService;

    @Override
    @WebMethod
    public ServerDTO serverInfo(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return new ServerDTO(System.getProperty("server.port"), System.getProperty("server.address"));
    }


    @Override
    @WebMethod(exclude = true)
    public void init(final String port) {
        System.out.println("http://localhost:" + port + "/ServerEndpoint?WSDL");
        Endpoint.publish("http://localhost:" + port + "/ServerEndpoint", this);
    }

    @WebMethod(exclude = true)
    public void sessionValidate(@Nullable final SessionDTO session) throws AccessForbiddenException, IncorrectDataException {
        if (session == null) throw new AccessForbiddenException();
        @Nullable final SessionDTO sessionDTO = sessionService.findOne(session.getId());
        if (sessionDTO == null) throw new AccessForbiddenException();
        if (session.getUserId() == null && !session.getUserId().isEmpty()) throw new AccessForbiddenException();
        if (session.getUserRole() == null) throw new AccessForbiddenException();
        if (!session.getSign().equals(sessionDTO.getSign())) throw new AccessForbiddenException();
    }

}
