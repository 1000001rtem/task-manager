package ru.eremin.tm.command.secured;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.Domain;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import java.io.File;
import java.io.IOException;

/**
 * @autor av.eremin on 18.04.2019.
 */

@NoArgsConstructor
public class DataLoadJacksonXMLCommand extends AbstractTerminalCommand {
    @Override
    public String getName() {
        return "data_load_jackson_xml";
    }

    @Override
    public String getDescription() {
        return "Load data from XML with Jackson";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @Nullable final Domain domain = xmlMapper.readValue(new File(path + "/data.xml"), Domain.class);
        if (domain == null || domain.getProjects() == null) return;
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        domain.getProjects().forEach(projectService::persist);
        if (domain.getTasks() == null) return;
        domain.getTasks().forEach(taskService::persist);
    }

}
