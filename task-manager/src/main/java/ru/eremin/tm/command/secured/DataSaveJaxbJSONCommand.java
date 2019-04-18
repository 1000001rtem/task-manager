package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor av.eremin on 18.04.2019.
 */

@NoArgsConstructor
public class DataSaveJaxbJSONCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_save_jaxb_json";
    }

    @Override
    public String getDescription() {
        return "Save data to JSON with jaxb";
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

    private void saveObject(@NotNull final Domain domain) throws JAXBException {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{Domain.class, ObjectFactory.class}, properties);
        @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(domain, new File(path + "/data.json"));
    }

}
