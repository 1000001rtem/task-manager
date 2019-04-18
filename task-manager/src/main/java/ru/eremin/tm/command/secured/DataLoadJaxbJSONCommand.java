package ru.eremin.tm.command.secured;

import lombok.NoArgsConstructor;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.command.AbstractTerminalCommand;
import ru.eremin.tm.model.dto.Domain;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @autor av.eremin on 18.04.2019.
 */

@NoArgsConstructor
public class DataLoadJaxbJSONCommand extends AbstractTerminalCommand {

    @Override
    public String getName() {
        return "data_load_jaxb_json";
    }

    @Override
    public String getDescription() {
        return "Load data to JSON with jaxb";
    }

    @Override
    public void setSecured() {

    }

    @Override
    public void execute() throws JAXBException {
        @Nullable final Domain domain = getDomainFromJSON();
        if (domain == null || domain.getProjects() == null) return;
        @NotNull final IProjectService projectService = locator.getProjectService();
        @NotNull final ITaskService taskService = locator.getTaskService();
        domain.getProjects().forEach(projectService::persist);
        if (domain.getTasks() == null) return;
        domain.getTasks().forEach(taskService::persist);
    }

    @Nullable
    private Domain getDomainFromJSON() throws JAXBException {
        @NotNull final String path = "documents/serialization/" + locator.getSession().getUser().getId();
        @NotNull final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{Domain.class, ObjectFactory.class}, properties);
        @NotNull final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final StreamSource source = new StreamSource(path + "/data.json");
        @NotNull final JAXBElement<Domain> jaxbElement = jaxbUnmarshaller.unmarshal(source, Domain.class);
        return jaxbElement.getValue();
    }

}
