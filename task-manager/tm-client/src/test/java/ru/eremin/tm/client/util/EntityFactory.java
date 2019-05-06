package ru.eremin.tm.client.util;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import ru.eremin.tm.server.endpoint.ProjectDTO;
import ru.eremin.tm.server.endpoint.TaskDTO;

import java.util.UUID;

/**
 * @autor av.eremin on 15.04.2019.
 */

public class EntityFactory {

    public static ProjectDTO getProject(final String userId) {
        final ProjectDTO project = new ProjectDTO();
        project.setId(UUID.randomUUID().toString());
        project.setName("testProject" + getRandom());
        project.setDescription("projectTestDescription" + getRandom());
        project.setStartDate(new XMLGregorianCalendarImpl());
        project.setEndDate(new XMLGregorianCalendarImpl());
        project.setUserId(userId);
        return project;
    }

    public static TaskDTO getTask(final ProjectDTO projectDTO) {
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("testTask" + getRandom());
        taskDTO.setDescription("testTaskDescription" + getRandom());
        taskDTO.setStartDate(new XMLGregorianCalendarImpl());
        taskDTO.setEndDate(new XMLGregorianCalendarImpl());
        taskDTO.setProjectId(projectDTO.getId());
        taskDTO.setUserId(projectDTO.getUserId());
        return taskDTO;
    }

    private static int getRandom() {
        return ((int) (Math.random() * 30));
    }
}
