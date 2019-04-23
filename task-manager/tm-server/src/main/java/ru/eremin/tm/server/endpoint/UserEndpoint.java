package ru.eremin.tm.server.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.endpoint.api.IUserEndpoint;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ResultDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.service.api.IUserService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class UserEndpoint extends AbstractEndpoint implements IUserEndpoint {

    @Nullable
    private IUserService userService;

    @Override
    @WebMethod
    public ResultDTO persistUser(@Nullable final SessionDTO sessionDTO, @Nullable final UserDTO userDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        userService.persist(new UserDTO(userDTO));
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public List<UserDTO> findAllUsers(@Nullable final SessionDTO sessionDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return userService.findAll();
    }

    @Override
    @WebMethod
    public UserDTO findOneUser(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return userService.findOne(id);
    }

    @Override
    @WebMethod
    public UserDTO findOneUserByLogin(@Nullable final SessionDTO sessionDTO, @Nullable final String login) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        return userService.findByLogin(login);
    }

    @Override
    @WebMethod
    public ResultDTO updateUser(@Nullable final SessionDTO sessionDTO, @Nullable final UserDTO userDTO) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        userService.update(userDTO);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod
    public ResultDTO removeUser(@Nullable final SessionDTO sessionDTO, @Nullable final String id) throws AccessForbiddenException, IncorrectDataException {
        sessionValidate(sessionDTO);
        userService.remove(id);
        return new ResultDTO(true);
    }

    @Override
    @WebMethod(exclude = true)
    public void init() {
        userService = locator.getUserService();
        System.out.println("http://localhost:8080/UserEndpoint?WSDL");
        Endpoint.publish("http://localhost:8080/UserEndpoint", this);
    }

}
