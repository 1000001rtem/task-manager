package ru.eremin.tm.client.command.secured;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eremin.tm.client.bootstrap.ServiceLocator;
import ru.eremin.tm.client.command.ICommand;
import ru.eremin.tm.client.service.ConsoleService;
import ru.eremin.tm.server.endpoint.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * @autor av.eremin on 10.04.2019.
 */

@Component
@NoArgsConstructor
public class TaskCreateCommand implements ICommand {

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
    private ServiceLocator locator;

    @Autowired
    private ConsoleService consoleService;

    @Override
    public String getName() {
        return "task_create";
    }

    @Override
    public String getDescription() {
        return "Create new task";
    }

    @Override
    public boolean getSecured() {
        return true;
    }

    @Override
    public void execute() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final TaskDTO task = getTask();
        taskEndpoint.persistTask(locator.getSession(), task);
    }

    @NotNull
    private TaskDTO getTask() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        @NotNull final String name = consoleService.getStringFieldFromConsole("Task name");
        @NotNull final String description = consoleService.getStringFieldFromConsole("Description");
        @Nullable final XMLGregorianCalendar startDate = consoleService.getDateFieldFromConsole("Start date");
        @Nullable final XMLGregorianCalendar endDate = consoleService.getDateFieldFromConsole("End date");
        @NotNull final String projectId = getProjectIdFromConsole();
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (endDate != null) task.setEndDate(endDate);
        task.setProjectId(projectId);
        task.setUserId(locator.getSession().getUserId());
        return task;
    }

    @NotNull
    private String getProjectIdFromConsole() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        String id;
        boolean flag;
        do {
            System.out.println("*** Please write project id ***");
            @NotNull final List<ProjectDTO> projectDTOS = projectEndpoint.findAllProjects(locator.getSession());
            projectDTOS.forEach(System.out::println);
            flag = true;
            id = consoleService.getNextLine();
            if (id == null || id.isEmpty()) {
                System.out.println("*** Wrong id ***");
                flag = false;
            }
        } while (!flag);
        return id;
    }

}
