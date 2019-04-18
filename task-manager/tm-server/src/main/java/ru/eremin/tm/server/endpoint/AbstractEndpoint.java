package ru.eremin.tm.server.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.bootstrap.ServiceLocator;

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

}
