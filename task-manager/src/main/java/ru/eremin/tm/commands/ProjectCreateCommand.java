package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.model.dto.ProjectDTO;

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
        @NotNull final String name = getStringFieldFromConsole("Project name");
        @NotNull final String description = getStringFieldFromConsole("Project Description");
        @NotNull final Date startDate = getDateFieldFromConsole("Start date");
        @NotNull final Date endDate = getDateFieldFromConsole("End date");
        @NotNull final ProjectDTO project = new ProjectDTO();
        project.setName(name);
        project.setDescription(description);
        if (startDate != null) project.setStartDate(startDate);
        if (endDate != null) project.setEndDate(endDate);
        return project;
    }

}
