package ru.eremin.tm.server.model.service.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.entity.session.Session;

/**
 * @autor av.eremin on 22.04.2019.
 */

public interface ISessionService extends IService<Session, SessionDTO> {

    String sign(@NotNull SessionDTO sessionDTO);

}
