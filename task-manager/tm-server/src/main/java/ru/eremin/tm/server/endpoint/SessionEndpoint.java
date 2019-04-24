package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.ISessionEndpoint;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.model.service.api.ISessionService;

import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

public class SessionEndpoint extends AbstractEndpoint implements ISessionEndpoint {

    @NotNull
    private ISessionService sessionService;

    @Override
    public ResultDTO persistSession(@Nullable final SessionDTO adminSession, @Nullable final SessionDTO newSession) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(adminSession);
        checkAdminRole(adminSession);
        sessionService.persist(newSession);
        return new ResultDTO(true);
    }

    @Override
    public List<SessionDTO> findAllSessions(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        checkAdminRole(sessionDTO);
        return sessionService.findAll();
    }

    @Override
    public SessionDTO findOneSession(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        checkAdminRole(sessionDTO);
        return sessionService.findOne(id);
    }

    @Override
    public ResultDTO updateSession(@Nullable final SessionDTO adminSession, @Nullable final SessionDTO updatedSession) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(adminSession);
        checkAdminRole(adminSession);
        sessionService.update(updatedSession);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO removeSession(@Nullable final SessionDTO adminSession, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(adminSession);
        checkAdminRole(adminSession);
        sessionService.remove(id);
        return new ResultDTO(true);
    }

    @Override
    public ResultDTO removeAllSessions(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        checkAdminRole(sessionDTO);
        sessionService.removeAll();
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init() {
        sessionService = locator.getSessionService();
        System.out.println("http://localhost:8080/SessionEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/SessionEndpoint", this);
    }

    @Override
    @WebMethod(exclude = true)
    public void checkAdminRole(@NotNull final SessionDTO sessionDTO) throws AccessForbiddenException {
        if (!sessionDTO.getUserRole().equals(Role.ADMIN)) throw new AccessForbiddenException("Need Admin rights");
    }

}
