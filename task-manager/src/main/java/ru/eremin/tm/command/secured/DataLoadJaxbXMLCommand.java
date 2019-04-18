package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

@NoArgsConstructor
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
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws JAXBException {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        @Nullable final Domain domain = (Domain) jaxbUnmarshaller.unmarshal(new File(path + "/data.xml"));
        if (domain == null || domain.getProjects() == null) return;
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        domain.getProjects().forEach(projectService::persist);
        if (domain.getTasks() == null) return;
        domain.getTasks().forEach(taskService::persist);
    }

}
