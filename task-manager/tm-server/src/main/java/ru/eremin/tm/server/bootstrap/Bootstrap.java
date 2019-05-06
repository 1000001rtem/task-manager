package ru.eremin.tm.server.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.server.api.*;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.security.IAuthService;
import ru.eremin.tm.server.utils.PasswordHashUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @autor av.eremin on 18.04.2019.
 */

@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @Inject
    @Getter
    @NotNull
    private IProjectService projectService;

    @Inject
    @Getter
    @NotNull
    private ITaskService taskService;

    @Inject
    @Getter
    @NotNull
    private IUserService userService;

    @Inject
    @Getter
    @NotNull
    private IAuthService authService;

    @Inject
    @Getter
    @NotNull
    private ISessionService sessionService;

    @Inject
    @Nullable
    private IAdminEndpoint adminEndpoint;

    @Inject
    @Nullable
    private IAuthorizationEndpoint authorizationEndpoint;

    @Inject
    @Nullable
    private IProjectEndpoint projectEndpoint;

    @Inject
    @Nullable
    private ISessionEndpoint sessionEndpoint;

    @Inject
    @Nullable
    private ITaskEndpoint taskEndpoint;

    @Inject
    @Nullable
    private IUserEndpoint userEndpoint;


    @Override
    public void init() {
        adminEndpoint.init();
        authorizationEndpoint.init();
        projectEndpoint.init();
        sessionEndpoint.init();
        taskEndpoint.init();
        userEndpoint.init();
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

        try {
            if (!userService.isExist(admin.getId())) userService.persist(admin);
            if (!userService.isExist(user.getId())) userService.persist(user);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }

    }

}
