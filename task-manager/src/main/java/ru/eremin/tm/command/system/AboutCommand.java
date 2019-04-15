package ru.eremin.tm.command.system;

import com.jcabi.manifests.Manifests;
import lombok.NoArgsConstructor;
import ru.eremin.tm.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 12.04.2019.
 */

@NoArgsConstructor
public class AboutCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "about";
    }

    @Override
    public String getDescription() {
        return "About App";
    }

    @Override
    public void setSecured() {
        this.isSecured = false;
    }

    @Override
    public void execute() {
        String created = Manifests.read("Build-Jdk");
        System.out.println("Build-version: " + created);
    }

}
