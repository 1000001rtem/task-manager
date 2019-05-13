package ru.eremin.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.SessionDTO;
import ru.eremin.tm.model.entity.session.Session;

/**
 * @autor av.eremin on 22.04.2019.
 */

public interface ISessionService extends IService<Session, SessionDTO> {

    SessionDTO findByUserId(@Nullable String userId) throws IncorrectDataException;

    String sign(@NotNull SessionDTO sessionDTO);

    void removeAll();

}
