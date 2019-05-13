package ru.eremin.tm.server.bootstrap;

import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.service.security.IAuthService;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface ServiceLocator {

    void init();

    IProjectService getProjectService();

    ITaskService getTaskService();

    IUserService getUserService();

    IAuthService getAuthService();

    ISessionService getSessionService();

}
