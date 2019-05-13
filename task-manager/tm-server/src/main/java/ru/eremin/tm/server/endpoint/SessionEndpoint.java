package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.server.api.ISessionEndpoint;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

@Component
@WebService
public class SessionEndpoint implements ISessionEndpoint {

    @NotNull
    @Autowired
    private ISessionService sessionService;

    @Override
    @WebMethod
    public ResultDTO persistSession(@Nullable final SessionDTO adminSession, @Nullable final SessionDTO newSession) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(adminSession);
        checkAdminRole(adminSession);
        sessionService.persist(newSession);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<SessionDTO> findAllSessions(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        checkAdminRole(sessionDTO);
        return sessionService.findAll();
    }

    @Override
    @WebMethod
    public SessionDTO findOneSession(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        checkAdminRole(sessionDTO);
        return sessionService.findOne(id);
    }

    @Override
    @WebMethod
    public ResultDTO updateSession(@Nullable final SessionDTO adminSession, @Nullable final SessionDTO updatedSession) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(adminSession);
        checkAdminRole(adminSession);
        sessionService.update(updatedSession);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public ResultDTO removeSession(@Nullable final SessionDTO adminSession, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(adminSession);
        checkAdminRole(adminSession);
        sessionService.remove(id);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public ResultDTO removeAllSessions(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        checkAdminRole(sessionDTO);
        sessionService.removeAll();
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init(String port) {
        System.out.println("http://localhost:" + port + "/SessionEndpoint?WSDL");
        Endpoint.publish("http://localhost:" + port + "/SessionEndpoint", this);
    }

    @Override
    @WebMethod(exclude = true)
    public void checkAdminRole(@NotNull final SessionDTO sessionDTO) throws AccessForbiddenException {
        if (!sessionDTO.getUserRole().equals(Role.ADMIN)) throw new AccessForbiddenException("Need Admin rights");
    }

    public void sessionValidate(@Nullable final SessionDTO session) throws AccessForbiddenException, IncorrectDataException {
        if (session == null) throw new AccessForbiddenException();
        @Nullable final SessionDTO sessionDTO = sessionService.findOne(session.getId());
        if (sessionDTO == null) throw new AccessForbiddenException();
        if (session.getUserId() == null && !session.getUserId().isEmpty()) throw new AccessForbiddenException();
        if (session.getUserRole() == null) throw new AccessForbiddenException();
        if (!session.getSign().equals(sessionDTO.getSign())) throw new AccessForbiddenException();
    }

}
