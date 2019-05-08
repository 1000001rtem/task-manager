package ru.eremin.tm.server.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ServerDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 08.05.2019.
 */

public interface IServerEndpoint {

    void init(String port);

    ServerDTO serverInfo(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

}
