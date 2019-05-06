package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.server.endpoint.*;

import javax.inject.Inject;

/**
 * @autor av.eremin on 18.04.2019.
 */

@NoArgsConstructor
public class DataLoadJacksonJSONCommand implements ICommand {

    @Inject
    private AdminEndpoint adminEndpoint;

    @Inject
    private ServiceLocator locator;

    @Override
    public String getName() {
        return "data_load_jackson_json";
    }

    @Override
    public String getDescription() {
        return "Load data from JSON with Jackson (for admin)";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        adminEndpoint.loadJSON(locator.getSession());
    }

}
