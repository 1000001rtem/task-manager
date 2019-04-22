package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.client.command.AbstractTerminalCommand;
import ru.eremin.tm.server.endpoint.AdminEndpoint;
import ru.eremin.tm.server.endpoint.AdminEndpointService;
import ru.eremin.tm.server.endpoint.SessionValidateExeption_Exception;

/**
 * @autor av.eremin on 18.04.2019.
 */

@NoArgsConstructor
public class DataSaveJacksonJSONCommand extends AbstractTerminalCommand {

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
    public void execute() throws SessionValidateExeption_Exception {
        @NotNull final AdminEndpointService adminEndpointService = new AdminEndpointService();
        @NotNull final AdminEndpoint adminEndpoint = adminEndpointService.getAdminEndpointPort();
        adminEndpoint.saveJSON(locator.getSession());
    }

}
