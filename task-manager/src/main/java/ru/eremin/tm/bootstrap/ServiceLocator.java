package ru.eremin.tm.bootstrap;

import ru.eremin.tm.model.entity.session.Session;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;
import ru.eremin.tm.model.service.api.IUserService;
import ru.eremin.tm.security.IAuthService;
import ru.eremin.tm.security.IRegistrationService;

import java.util.Scanner;

/**
 * @autor av.eremin on 12.04.2019.
 */

public interface ServiceLocator {

    void init();

    IProjectService getProjectService();

    ITaskService getTaskService();

    IUserService getUserService();

    IAuthService getAuthService();

    IRegistrationService getRegistrationService();

    Scanner getScanner();

    Session getSession();

    void setSession(Session session);

}
