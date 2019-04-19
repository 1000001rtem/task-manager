package ru.eremin.tm.server.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.bootstrap.ServiceLocator;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.entity.session.Session;

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

    public void sessionValidate(@Nullable final Session session) throws SessionValidateExeption {
        if (session == null) throw new SessionValidateExeption();
        if (!session.getSign().equals(locator.getSession().getSign())) throw new SessionValidateExeption();
    }

}
