package ru.eremin.tm.backend.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.backend.exeption.AccessForbiddenException;
import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.web.LoginRequest;
import ru.eremin.tm.backend.model.dto.web.ResponseAuthEntity;

import javax.jws.WebService;

/**
 * @autor av.eremin on 31.05.2019.
 */

@WebService
public interface AuthEndpoint {

    ResponseAuthEntity auth(@Nullable LoginRequest loginRequest) throws AccessForbiddenException, IncorrectDataException;

}
