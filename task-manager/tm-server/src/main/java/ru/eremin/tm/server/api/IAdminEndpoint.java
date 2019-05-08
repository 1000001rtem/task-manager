package ru.eremin.tm.server.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 19.04.2019.
 */

public interface IAdminEndpoint extends Admined {

    void init(String port);

    ResultDTO saveJSON(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO loadJSON(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO clearJSON(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

}
