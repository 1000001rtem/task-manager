package ru.eremin.tm.server.bootstrap;

import ru.eremin.tm.server.model.service.api.IProjectService;
import ru.eremin.tm.server.model.service.api.ISessionService;
import ru.eremin.tm.server.model.service.api.ITaskService;
import ru.eremin.tm.server.model.service.api.IUserService;
import ru.eremin.tm.server.security.IAuthService;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface ServiceLocator {

    void init(Class[] classes);

    IProjectService getProjectService();

    ITaskService getTaskService();

    IUserService getUserService();

    IAuthService getAuthService();

    ISessionService getSessionService();

}
