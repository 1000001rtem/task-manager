package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor av.eremin on 11.04.2019.
 */

@NoArgsConstructor
public class DataBeanSaveCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_bean_save";
    }

    @Override
    public String getDescription() {
        return "Save data to binary file";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() throws IOException {
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        @NotNull final List<ProjectDTO> projects = projectService.findAll(locator.getSession().getUser().getId());
        @NotNull final Map<ProjectDTO, List<TaskDTO>> map = new HashMap<>();
        for (final ProjectDTO project : projects) {
            map.put(project, taskService.findByProjectId(project.getId()));
        }
        saveObject(map);
    }

    private void saveObject(@NotNull final Map<ProjectDTO, List<TaskDTO>> map) throws IOException {
        if (map.isEmpty()) return;
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final File file = new File(path);
        file.mkdirs();
        @NotNull final ObjectOutputStream objectOutPutStream
                = new ObjectOutputStream(new FileOutputStream(path + "/data.ser"));
        objectOutPutStream.writeObject(map);
        objectOutPutStream.close();
    }

}
