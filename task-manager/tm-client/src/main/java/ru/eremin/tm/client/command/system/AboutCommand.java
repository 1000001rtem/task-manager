package ru.eremin.tm.client.command.system;

import com.jcabi.manifests.Manifests;
import lombok.NoArgsConstructor;
import ru.eremin.tm.client.command.AbstractTerminalCommand;

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
        return "About Application";
    }

    @Override
    public boolean getSecured() {
        return false;
    }

    @Override
    public void execute() {
        String created = Manifests.read("Build-Jdk");
        System.out.println("Build-version: " + created);
    }

}
