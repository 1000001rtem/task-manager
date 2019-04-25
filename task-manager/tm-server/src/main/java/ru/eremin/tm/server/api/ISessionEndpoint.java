package ru.eremin.tm.server.api;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;

import java.util.List;

/**
 * @autor av.eremin on 24.04.2019.
 */

public interface ISessionEndpoint extends Admined {

    ResultDTO persistSession(@Nullable SessionDTO adminSession, @Nullable SessionDTO newSession) throws AccessForbiddenException, IncorrectDataException;

    List<SessionDTO> findAllSessions(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

    SessionDTO findOneSession(@Nullable SessionDTO sessionDTO, @Nullable String id) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO updateSession(@Nullable SessionDTO adminSession, @Nullable SessionDTO updatedSession) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO removeSession(@Nullable SessionDTO adminSession, @Nullable String id) throws AccessForbiddenException, IncorrectDataException;

    ResultDTO removeAllSessions(@Nullable SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException;

}
