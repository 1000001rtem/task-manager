package ru.eremin.tm.server.model.service.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.session.Session;

/**
 * @autor av.eremin on 22.04.2019.
 */

public interface ISessionService extends IService<Session, SessionDTO> {

    SessionDTO findByUserId(@Nullable String userId) throws IncorrectDataException;

    String sign(@NotNull SessionDTO sessionDTO);

}
