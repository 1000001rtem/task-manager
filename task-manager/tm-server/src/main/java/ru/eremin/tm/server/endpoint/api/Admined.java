package ru.eremin.tm.server.endpoint.api;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.model.dto.SessionDTO;

/**
 * @autor av.eremin on 24.04.2019.
 */

public interface Admined {

    void checkAdminRole(@NotNull final SessionDTO sessionDTO) throws AccessForbiddenException;

}
