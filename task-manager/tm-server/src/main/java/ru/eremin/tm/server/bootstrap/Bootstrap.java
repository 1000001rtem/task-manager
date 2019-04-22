package ru.eremin.tm.server.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.server.endpoint.AbstractEndpoint;
import ru.eremin.tm.server.exeption.IncorrectClassException;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.model.repository.ProjectRepository;
import ru.eremin.tm.server.model.repository.SessionRepository;
import ru.eremin.tm.server.model.repository.TaskRepository;
import ru.eremin.tm.server.model.repository.UserRepository;
import ru.eremin.tm.server.model.repository.api.IProjectRepository;
import ru.eremin.tm.server.model.repository.api.ISessionRepository;
import ru.eremin.tm.server.model.repository.api.ITaskRepository;
import ru.eremin.tm.server.model.repository.api.IUserRepository;
import ru.eremin.tm.server.model.service.ProjectService;
import ru.eremin.tm.server.model.service.SessionService;
import ru.eremin.tm.server.model.service.TaskService;
import ru.eremin.tm.server.model.service.UserService;
import ru.eremin.tm.server.model.service.api.IProjectService;
import ru.eremin.tm.server.model.service.api.ISessionService;
import ru.eremin.tm.server.model.service.api.ITaskService;
import ru.eremin.tm.server.model.service.api.IUserService;
import ru.eremin.tm.server.security.AuthService;
import ru.eremin.tm.server.security.IAuthService;
import ru.eremin.tm.server.utils.PasswordHashUtil;

/**
 * @autor av.eremin on 18.04.2019.
 */

public class Bootstrap implements ServiceLocator {

    @NotNull
    @Getter
    private final IProjectService projectService;

    @NotNull
    @Getter
    private final ITaskService taskService;

    @NotNull
    @Getter
    private final IUserService userService;

    @NotNull
    @Getter
    private final IAuthService authService;

    @NotNull
    @Getter
    private final ISessionService sessionService;

    public Bootstrap() {
        @NotNull final IProjectRepository projectRepository = new ProjectRepository();
        @NotNull final ITaskRepository taskRepository = new TaskRepository();
        @NotNull final IUserRepository userRepository = new UserRepository();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository();
        this.taskService = new TaskService(taskRepository);
        this.projectService = new ProjectService(projectRepository, this.taskService);
        this.userService = new UserService(userRepository);
        this.authService = new AuthService(userService);
        this.sessionService = new SessionService(sessionRepository);
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
        initUsers();
    }

    private AbstractEndpoint initEndpoint(final Class endpointClass) throws IncorrectClassException, IllegalAccessException, InstantiationException {
        if (!endpointClass.getSuperclass().equals(AbstractEndpoint.class)) {
            throw new IncorrectClassException("command super class is not AbstractEndpoint");
        }
        return (AbstractEndpoint) endpointClass.newInstance();
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

        userService.persist(user);
        userService.persist(admin);
    }

}
