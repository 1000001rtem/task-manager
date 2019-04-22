package ru.eremin.tm.server.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.bootstrap.ServiceLocator;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 18.04.2019.
 */

@NoArgsConstructor
public abstract class AbstractEndpoint {

    protected ServiceLocator locator;


    public abstract void init();

    public void setLocator(@Nullable final ServiceLocator serviceLocator) {
        if (serviceLocator == null) throw new NullPointerException("ServiceLocator == null");
        this.locator = serviceLocator;
    }

    public void sessionValidate(@Nullable final SessionDTO session) throws SessionValidateExeption {
        if (session == null) throw new SessionValidateExeption();
        @Nullable final SessionDTO sessionDTO = locator.getSessionService().findOne(session.getId());
        if (sessionDTO == null) throw new SessionValidateExeption();
        if (session.getUserId() == null && !session.getUserId().isEmpty()) throw new SessionValidateExeption();
        if (session.getUserRole() == null) throw new SessionValidateExeption();
        if (!session.getSign().equals(sessionDTO.getSign())) throw new SessionValidateExeption();
    }

}
