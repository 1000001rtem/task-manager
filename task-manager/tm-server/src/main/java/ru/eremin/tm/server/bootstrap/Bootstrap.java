package ru.eremin.tm.server.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.server.api.*;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.service.security.IAuthService;
import ru.eremin.tm.server.utils.PasswordHashUtil;


/**
 * @autor av.eremin on 18.04.2019.
 */

@Component(Bootstrap.NAME)
public class Bootstrap implements ServiceLocator {

    public static final String NAME = "bootstrap";

    @Getter
    @NotNull
    @Autowired
    private IProjectService projectService;

    @Getter
    @NotNull
    @Autowired
    private ITaskService taskService;

    @Getter
    @NotNull
    @Autowired
    private IUserService userService;

    @Getter
    @NotNull
    @Autowired
    private IAuthService authService;

    @Getter
    @NotNull
    @Autowired
    private ISessionService sessionService;

    @Nullable
    @Autowired
    private IAdminEndpoint adminEndpoint;

    @Nullable
    @Autowired
    private IAuthorizationEndpoint authorizationEndpoint;

    @Nullable
    @Autowired
    private IProjectEndpoint projectEndpoint;

    @Nullable
    @Autowired
    private ISessionEndpoint sessionEndpoint;

    @Nullable
    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Nullable
    @Autowired
    private IUserEndpoint userEndpoint;

    @Nullable
    @Autowired
    private IServerEndpoint serverEndpoint;


    @Override
    public void init() {
        if (System.getProperty("server.port") == null) System.setProperty("server.port", "8080");
        String port = System.getProperty("server.port");
        System.setProperty("server.address", "localhost");
        adminEndpoint.init(port);
        authorizationEndpoint.init(port);
        projectEndpoint.init(port);
        sessionEndpoint.init(port);
        taskEndpoint.init(port);
        userEndpoint.init(port);
        serverEndpoint.init(port);
        initUsers();
    }

    private void initUsers() {
        @NotNull final UserDTO user = new UserDTO();
        user.setId("6bf0f091-e795-42d1-bb9a-77799cdf37da");
        user.setLogin("user");
        user.setHashPassword(PasswordHashUtil.md5("pass"));
        user.setRole(Role.USER);

        @NotNull final UserDTO admin = new UserDTO();
        admin.setId("6706e691-2f78-45ad-b021-3730c48959f0");
        admin.setLogin("admin");
        admin.setHashPassword(PasswordHashUtil.md5("pass"));
        admin.setRole(Role.ADMIN);

        @NotNull final UserDTO test = new UserDTO();
        test.setId("8ea5b36e-537e-44c3-92a2-c79ef0900dcf");
        test.setLogin("test");
        test.setHashPassword(PasswordHashUtil.md5("test"));
        test.setRole(Role.ADMIN);

        try {
            if (!userService.isExist(admin.getId())) userService.persist(admin);
            if (!userService.isExist(user.getId())) userService.persist(user);
            if (!userService.isExist(test.getId())) userService.persist(test);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }

    }

}
