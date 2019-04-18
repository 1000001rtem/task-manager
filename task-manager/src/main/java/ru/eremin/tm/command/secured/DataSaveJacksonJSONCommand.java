package ru.eremin.tm.command.secured;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.Domain;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
        return "Save data to JSON with Jackson";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IOException {
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        @NotNull final List<ProjectDTO> projects = projectService.findAll(locator.getSession().getUser().getId());
        @NotNull final List<TaskDTO> tasks = taskService.findAll(locator.getSession().getUser().getId());
        @NotNull final Domain domain = new Domain(projects, tasks);
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        mapper.writeValue(new File(path + "/data.json"), domain);
    }
}
