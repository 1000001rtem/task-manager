package ru.eremin.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.eremin.tm.api.endpoint.AuthEndpoint;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.dto.web.LoginRequest;
import ru.eremin.tm.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.security.JwtTokenProvider;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor av.eremin on 31.05.2019.
 */

@WebService(endpointInterface = "ru.eremin.tm.api.endpoint.AuthEndpoint")
public class AuthEndpointImpl implements AuthEndpoint {

    @NotNull
    @Autowired
    private IUserService userService;

    @NotNull
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @NotNull
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseAuthEntity auth(@Nullable final LoginRequest loginRequest) throws AccessForbiddenException, IncorrectDataException {
        if (loginRequest == null) throw new AccessForbiddenException();

        @Nullable final String login = loginRequest.getLogin();
        @Nullable final String password = loginRequest.getPassword();

        @NotNull final UserDTO userDTO = userService.findByLogin(login);
        @Nullable final List<String> roles = new ArrayList<>();
        roles.add(userDTO.getRole().name());

        @NotNull final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        @Nullable final String token = jwtTokenProvider.createToken(login, roles);
        return new ResponseAuthEntity(userDTO.getId(), login, token);
    }

}
