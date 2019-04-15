package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import ru.eremin.tm.command.AbstractTerminalCommand;

/**
 * @autor av.eremin on 11.04.2019.
 */

@NoArgsConstructor
public class DataBeanCleanCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_bean_clean";
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

    }

}
