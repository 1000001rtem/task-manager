package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;

import java.io.File;

/**
 * @autor av.eremin on 11.04.2019.
 */

@NoArgsConstructor
public class DataBeanClearCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_bean_clear";
    }

    @Override
    public String getDescription() {
        return "Remove binary file";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final File file = new File(path + "/data.ser");
        file.delete();
    }

}
