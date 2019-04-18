package ru.eremin.tm.command.secured;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.exeption.BadCommandException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.Domain;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @autor av.eremin on 18.04.2019.
 */

@NoArgsConstructor
public class DataSaveJacksonXMLCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_save_jackson_xml";
    }

    @Override
    public String getDescription() {
        return "Save data to XML with Jackson";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() throws BadCommandException, IncorrectDataException, IOException, ClassNotFoundException, JAXBException {
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        @NotNull final List<ProjectDTO> projects = projectService.findAll(locator.getSession().getUser().getId());
        @NotNull final List<TaskDTO> tasks = taskService.findAll(locator.getSession().getUser().getId());
        @NotNull final Domain domain = new Domain(projects, tasks);
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        xmlMapper.writeValue(new File(path + "/data.xml"), domain);
    }

}
