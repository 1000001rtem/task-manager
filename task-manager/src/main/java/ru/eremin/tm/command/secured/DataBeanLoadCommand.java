package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @autor av.eremin on 11.04.2019.
 */

@NoArgsConstructor
public class DataBeanLoadCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_bean_load";
    }

    @Override
    public String getDescription() {
        return "Load data from binary file";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        @NotNull final Map<ProjectDTO, List<TaskDTO>> projects = getProjectFromBinaryFile();
        if (projects.isEmpty()) return;
        for (final Map.Entry<ProjectDTO, List<TaskDTO>> entry : projects.entrySet()) {
            projectService.persist(entry.getKey());
            entry.getValue().forEach(taskService::persist);
        }
    }

    @NotNull
    private Map<ProjectDTO, List<TaskDTO>> getProjectFromBinaryFile() throws IOException, ClassNotFoundException {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path + "/data.ser"));
        Map<ProjectDTO, List<TaskDTO>> projects;
        boolean isExist = true;
        while (isExist) {
            projects = (Map<ProjectDTO, List<TaskDTO>>) objectInputStream.readObject();
            if (projects == null) {
                isExist = false;
            } else {
                objectInputStream.close();
                return projects;
            }
        }
        objectInputStream.close();
        return Collections.emptyMap();
    }

}
