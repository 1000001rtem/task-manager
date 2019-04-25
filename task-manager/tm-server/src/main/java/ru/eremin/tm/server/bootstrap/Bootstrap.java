package ru.eremin.tm.server.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.endpoint.AbstractEndpoint;
import ru.eremin.tm.server.exeption.IncorrectClassException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.security.AuthService;
import ru.eremin.tm.server.security.IAuthService;
import ru.eremin.tm.server.service.ProjectService;
import ru.eremin.tm.server.service.SessionService;
import ru.eremin.tm.server.service.TaskService;
import ru.eremin.tm.server.service.UserService;
import ru.eremin.tm.server.utils.DBConnectionUtils;
import ru.eremin.tm.server.utils.PasswordHashUtil;

import java.sql.Connection;

/**
 * @autor av.eremin on 18.04.2019.
 */

public class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final IProjectService projectService;

    @Getter
    @NotNull
    private final ITaskService taskService;

    @Getter
    @NotNull
    private final IUserService userService;

    @Getter
    @NotNull
    private final IAuthService authService;

    @Getter
    @NotNull
    private final ISessionService sessionService;

    @Getter
    @NotNull
    private final Connection connection;

    public Bootstrap() {
        this.connection = DBConnectionUtils.getConnection();
        this.taskService = new TaskService();
        this.projectService = new ProjectService(this.taskService);
        this.userService = new UserService();
        this.authService = new AuthService(userService);
        this.sessionService = new SessionService();
    }

    @Override
    public void init(final Class[] classes) {
        for (final Class endpointClass : classes) {
            AbstractEndpoint endpoint;
            try {
                endpoint = initEndpoint(endpointClass);
                endpoint.setLocator(this);
                endpoint.init();
            } catch (IncorrectClassException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        try {
            initUsers();
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
    }

    private AbstractEndpoint initEndpoint(final Class endpointClass) throws IncorrectClassException, IllegalAccessException, InstantiationException {
        if (!endpointClass.getSuperclass().equals(AbstractEndpoint.class)) {
            throw new IncorrectClassException("command super class is not AbstractEndpoint");
        }
        return (AbstractEndpoint) endpointClass.newInstance();
    }

    private void initUsers() throws IncorrectDataException {
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

        if (userService.findOne(user.getId()) == null) userService.persist(user);
        if (userService.findOne(admin.getId()) == null) userService.persist(admin);
    }

}
