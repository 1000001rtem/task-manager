package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.entity.session.Session;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IAuthorizationEndpoint {

    Session login(@Nullable String login, @Nullable String hashPassword);

}
