package ru.eremin.tm.command.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.Domain;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @autor av.eremin on 17.04.2019.
 */

public class DataLoadJaxbXMLCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_load_jaxb_xml";
    }

    @Override
    public String getDescription() {
        return "Load data to XML with jaxb";
    }

    @Override
    public void setSecured() {
        this.isSecured = true;
    }

    @Override
    public void execute() throws JAXBException {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final Domain employee = (Domain) jaxbUnmarshaller.unmarshal(new File(path + "/data.xml"));
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        if (employee.getProjects() == null) return;
        employee.getProjects().forEach(projectService::persist);
        if (employee.getTasks() == null) return;
        employee.getTasks().forEach(taskService::persist);
    }

}
