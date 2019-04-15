package ru.eremin.tm.commands.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.bootstrap.ServiceLocator;


/**
 * @autor av.eremin on 10.04.2019.
 */
@NoArgsConstructor
public abstract class AbstractTerminalCommand {

    @Getter
    protected boolean isSecured = true;

    protected ServiceLocator locator;

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();

    public void setLocator(@Nullable final ServiceLocator serviceLocator){
        if(serviceLocator == null) throw new NullPointerException("ServiceLocator == null");
        this.locator = serviceLocator;
    }

}
