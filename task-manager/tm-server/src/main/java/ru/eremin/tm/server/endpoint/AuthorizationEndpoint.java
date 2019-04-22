package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.IAuthorizationEndpoint;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.security.IAuthService;
import ru.eremin.tm.server.utils.PasswordHashUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @autor av.eremin on 19.04.2019.
 */

@WebService
public class AuthorizationEndpoint extends AbstractEndpoint implements IAuthorizationEndpoint {

    @Nullable
    private IAuthService authService;

    @Override
    @WebMethod
    public SessionDTO login(@Nullable final String login, @Nullable final String hashPassword) {
        if (login == null || login.isEmpty() || hashPassword == null || hashPassword.isEmpty()) return null;
        @Nullable final SessionDTO session = authService.login(login, PasswordHashUtil.md5(hashPassword));
        if (session == null) return null;
        session.setSign(locator.getSessionService().sign(session));
        locator.getSessionService().persist(session);
        return session; //TODO: check session
    }

    @Override
    public ResultDTO logout(@Nullable final SessionDTO sessionDTO) throws SessionValidateExeption, IncorrectDataException {
        sessionValidate(sessionDTO);
        locator.getSessionService().remove(sessionDTO.getId());
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init() {
        authService = locator.getAuthService();
        System.out.println("http://localhost:8080/AuthorizationEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/AuthorizationEndpoint", this);
    }

}
