package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.Domain;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import javax.validation.constraints.Null;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
        @Nullable final Domain domain = getDomainFromBinaryFile();
        if (domain == null || domain.getProjects() == null) return;
        domain.getProjects().forEach(projectService::persist);
        if (domain.getTasks() == null) return;
        domain.getTasks().forEach(taskService::persist);
    }

    @Nullable
    private Domain getDomainFromBinaryFile() throws IOException, ClassNotFoundException {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path + "/data.ser"));
        @Nullable final Domain domain = (Domain) objectInputStream.readObject();
        objectInputStream.close();
        return domain;
    }

}
