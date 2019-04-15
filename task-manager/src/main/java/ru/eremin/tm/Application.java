package ru.eremin.tm;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.commands.*;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.commands.secured.*;

public class Application {

    private static final Class[] CLASSES= {
            AboutCommand.class, HelpCommand.class, ExitCommand.class,
            AuthorizationCommand.class, UserRegistrationCommand.class,

            UserChangePasswordCommand.class, UserInfoCommand.class, UserUpdateCommand.class,

            TaskCreateCommand.class, TaskClearCommand.class, TaskInfoCommand.class,
            TaskRemoveCommand.class, TaskShowAllCommand.class, TaskShowInProjectCommand.class,

            ProjectCreateCommand.class, ProjectClearCommand.class, ProjectInfoCommand.class,
            ProjectRemoveCommand.class, ProjectShowAllCommand.class,

            DataBeanLoadCommand.class, DataBeanSaveCommand.class, DataBeanCleanCommand.class,

            LogoutCommand.class
    };

    public static void main(String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(CLASSES);
    }
}
