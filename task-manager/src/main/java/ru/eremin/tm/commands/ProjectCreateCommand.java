package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.utils.ConsoleHelper;

import java.util.Date;

/**
 * @autor av.eremin on 10.04.2019.
 */

public class ProjectCreateCommand extends AbstractTerminalCommand {

    @NotNull
    private static final CommandEnum command = CommandEnum.PROJECT_CREATE;

    public ProjectCreateCommand(@NotNull final Bootstrap bootstrap) {
        super(bootstrap);
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
        bootstrap.getProjectService().persist(project);
        System.out.println("*** Project created: " + project + " ***");
    }

    @NotNull
    private ProjectDTO getProject() {
        @NotNull final ConsoleHelper helper = new ConsoleHelper(bootstrap.getScanner());
        @NotNull final String name = helper.getStringFieldFromConsole("Project name");
        @NotNull final String description = helper.getStringFieldFromConsole("Project Description");
        @NotNull final Date startDate = helper.getDateFieldFromConsole("Start date");
        @NotNull final Date endDate = helper.getDateFieldFromConsole("End date");
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName(name);
        project.setDescription(description);
        if (startDate != null) project.setStartDate(startDate);
        if (endDate != null) project.setEndDate(endDate);
        return project;
    }

}
