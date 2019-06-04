package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.LoginRequest;
import ru.eremin.tm.model.dto.ResponseSoapEntity;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor av.eremin on 31.05.2019.
 */

@RestController
@RequestMapping(value = "/api")
public class AuthController {

    @NotNull
    @Autowired
    private IUserService userService;

    @NotNull
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @NotNull
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @NotNull
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseSoapEntity auth(@RequestBody @Nullable final LoginRequest loginRequest) throws AccessForbiddenException, IncorrectDataException {
        if (loginRequest == null) throw new AccessForbiddenException();

        @Nullable final String login = loginRequest.getLogin();
        @Nullable final String password = loginRequest.getPassword();

        @NotNull final UserDTO userDTO = userService.findByLogin(login);
        @Nullable final List<String> roles = new ArrayList<>();
        roles.add(userDTO.getRole().name());

        @NotNull final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        @Nullable final String token = jwtTokenProvider.createToken(login, roles);
        @Nullable final Map<Object, Object> map = new HashMap<>();
        map.put("login", login);
        map.put("token", token);
        return new ResponseSoapEntity(login, token);
    }

}
