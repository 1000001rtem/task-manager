package ru.eremin.tm.commands.secured;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.ServiceLocator;
import ru.eremin.tm.commands.base.AbstractTerminalCommand;
import ru.eremin.tm.commands.base.CommandEnum;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.utils.ConsoleHelper;

import java.util.Date;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class ProjectCreateCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.PROJECT_CREATE;

    public ProjectCreateCommand(@NotNull final ServiceLocator locator) {
        super(locator);
    }

    @Override
    public String getName() {
        return command.getName();
    }

    @Override
    public String getDescription() {
        return command.getDescription();
    }

    @Override
    public void execute() {
        @NotNull final ProjectDTO project = getProject();
        locator.getProjectService().persist(project);
        System.out.println("*** Project created: " + project + " ***");
    }

    @NotNull
    private ProjectDTO getProject() {
        @NotNull final ConsoleHelper helper = new ConsoleHelper(locator.getScanner());
        @NotNull final String name = helper.getStringFieldFromConsole("Project name");
        @NotNull final String description = helper.getStringFieldFromConsole("Project Description");
        @NotNull final Date startDate = helper.getDateFieldFromConsole("Start date");
        @NotNull final Date endDate = helper.getDateFieldFromConsole("End date");
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName(name);
        project.setDescription(description);
        if (startDate != null) project.setStartDate(startDate);
        if (endDate != null) project.setEndDate(endDate);
        project.setUserId(locator.getSession().getUser().getId());
        return project;
    }

}
