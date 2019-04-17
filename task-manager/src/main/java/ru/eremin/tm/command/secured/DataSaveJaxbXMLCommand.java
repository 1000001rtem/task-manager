package ru.eremin.tm.command.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.Domain;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * @autor av.eremin on 17.04.2019.
 */

public class DataSaveJaxbXMLCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_save_jaxb_xml";
    }

    @Override
    public String getDescription() {
        return "Save data to XML with jaxb";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() throws JAXBException {
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        @NotNull final List<ProjectDTO> projects = projectService.findAll(locator.getSession().getUser().getId());
        @NotNull final List<TaskDTO> tasks = taskService.findAll(locator.getSession().getUser().getId());
        @NotNull final Domain domain = new Domain(projects, tasks);
        saveObject(domain);
    }

    private void saveObject(@NotNull Domain domain) throws JAXBException {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(domain, new File(path + "/data.xml"));
    }

}
