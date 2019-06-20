package ru.eremin.tm.backend.rest;

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
import ru.eremin.tm.backend.api.service.IUserService;
import ru.eremin.tm.backend.exeption.AccessForbiddenException;
import ru.eremin.tm.backend.exeption.IncorrectDataException;
import ru.eremin.tm.backend.model.dto.UserDTO;
import ru.eremin.tm.backend.model.dto.web.LoginRequest;
import ru.eremin.tm.backend.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.backend.security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor av.eremin on 31.05.2019.
 */

@RestController
@RequestMapping(value = "/api")
public class AuthRestController {

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
    public ResponseAuthEntity auth(@RequestBody @Nullable final LoginRequest loginRequest) throws AccessForbiddenException, IncorrectDataException {
        if (loginRequest == null) throw new AccessForbiddenException();

        @Nullable final String login = loginRequest.getLogin();
        @Nullable final String password = loginRequest.getPassword();

        @NotNull final UserDTO userDTO = userService.findByLogin(login);
        @Nullable final List<String> roles = new ArrayList<>();
        roles.add(userDTO.getRole().name());

        @NotNull final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        @Nullable final String token = jwtTokenProvider.createToken(login, roles);
        return new ResponseAuthEntity(userDTO.getId(), login, token, userDTO.getRole());
    }

}
