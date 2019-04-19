package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.IAuthorizationEndpoint;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.security.IAuthService;
import ru.eremin.tm.server.utils.PasswordHashUtil;
import ru.eremin.tm.server.utils.SignatureUtil;

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
    public Session login(@Nullable final String login, @Nullable final String hashPassword) {
        if (login == null || login.isEmpty() || hashPassword == null || hashPassword.isEmpty()) return null;
        @Nullable final Session session = authService.login(login, PasswordHashUtil.md5(hashPassword));
        if (session == null) return null;
        session.setSign(sessionSign(session));
        locator.setSession(session);
        return session;
    }

    @Override
    public void init() {
        authService = locator.getAuthService();
        System.out.println("http://localhost:8080/AuthorizationEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/AuthorizationEndpoint", this);
    }

    private String sessionSign(@NotNull final Session session) {
        return SignatureUtil.sign(session, "#6df5&f", 10);
    }

}
