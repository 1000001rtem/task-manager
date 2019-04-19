package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.entity.session.Session;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IAdminEndpoint {

    ResultDTO saveJSON(@Nullable Session session);

    ResultDTO loadJSON(@Nullable Session session);

    ResultDTO clearJSON(@Nullable Session session);

    boolean checkSession(@NotNull Session session);

    boolean checkAdminRole(@NotNull Session session);

}
