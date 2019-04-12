package ru.eremin.tm.commands.secured;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class AboutCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.ABOUT;

    public AboutCommand(@NotNull final ServiceLocator locator) {
        super(locator);
        this.isSecured = false;
    }

    @Override
    public String getName() {
        return command.getName();
    }

    @Override
    public String getDescription() {
        return command.getDescription();
    }

    @Override
    public void execute() {
        String created = Manifests.read("Build-Jdk");
        System.out.println("Build-version: " + created);
    }

}
