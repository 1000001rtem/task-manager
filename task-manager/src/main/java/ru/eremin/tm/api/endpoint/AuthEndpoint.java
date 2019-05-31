package ru.eremin.tm.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.LoginRequest;
import ru.eremin.tm.model.dto.ResponseSoapEntity;

import javax.jws.WebService;

/**
 * @autor av.eremin on 31.05.2019.
 */

@WebService
public interface AuthEndpoint {

    ResponseSoapEntity auth(@Nullable LoginRequest loginRequest) throws AccessForbiddenException, IncorrectDataException;

}
