package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.server.endpoint.AccessForbiddenException_Exception;
import ru.eremin.tm.server.endpoint.AdminEndpoint;
import ru.eremin.tm.server.endpoint.IncorrectDataException_Exception;

/**
 * @autor av.eremin on 18.04.2019.
 */

@Component
@NoArgsConstructor
public class DataSaveJacksonJSONCommand implements ICommand {

    @Autowired
    private AdminEndpoint adminEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Override
    public String getName() {
        return "data_save_jackson_json";
    }

    @Override
    public String getDescription() {
        return "Save data to JSON with Jackson (for admin)";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        adminEndpoint.saveJSON(locator.getSession());
    }

}
