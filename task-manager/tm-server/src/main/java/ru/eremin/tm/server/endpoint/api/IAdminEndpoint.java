package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.SessionValidateExeption;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IAdminEndpoint {

    ResultDTO saveJSON(@Nullable SessionDTO session) throws SessionValidateExeption;

    ResultDTO loadJSON(@Nullable SessionDTO session) throws SessionValidateExeption;

    ResultDTO clearJSON(@Nullable SessionDTO session) throws SessionValidateExeption;

    boolean checkAdminRole(@NotNull SessionDTO session);

}
