package ru.eremin.tm;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.utilities.Commands;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.ProjectService;
import ru.eremin.tm.model.service.TaskService;
import ru.eremin.tm.utilities.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class ConsoleManager {

    private final ProjectService projectService;

    private final TaskService taskService;

    private final Scanner scanner;

    public ConsoleManager() {
        this.scanner = new Scanner(System.in);
        this.projectService = ProjectService.INSTANCE;
        this.taskService = TaskService.INSTANCE;
        start();
    }

    private void start() {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        Commands answer;
        while (true) {
            answer = parseLine(scanner.nextLine());
            if (answer == null) {
                System.out.println("*** WRONG COMMAND ***");
                answer = Commands.HELP;
            }
            switch (answer) {
                case HELP:
                    showHelp();
                    break;
                case EXIT:
                    exit();
                    break;
                case DATA_BEAN_LOAD:
                    loadFromBinaryFile();
                    break;
                case DATA_BEAN_SAVE:
                    saveToBinaryFile();
                    break;
                case DATA_BEAN_CLEAN:
                    deleteBinaryFile();
                    break;
                case PROJECT_CREATE:
                    createProject();
                    break;
                case PROJECT_LIST:
                    showAllProjects();
                    break;
                case PROJECT_INFO:
                    showProjectInfo();
                    break;
                case PROJECT_REMOVE:
                    removeProject();
                    break;
                case PROJECT_CLEAR:
                    removeAllProjects();
                    break;
                case TASK_CREATE:
                    createTask();
                    break;
                case TASK_LIST:
                    showAllTasks();
                    break;
                case TASK_INFO:
                    showTaskInfo();
                    break;
                case TASK_IN_PROJECT:
                    showTaskInProject();
                    break;
                case TASK_REMOVE:
                    removeTask();
                    break;
                case TASK_CLEAR:
                    removeAllTasks();
                    break;
            }
        }
    }

    private void showHelp() {
        for (final Commands command : Commands.values()) {
            System.out.println(command + ": " + command.getDescription());
        }
    }

    private void exit() {
        System.out.println("*** GOODBYE ***");
        System.exit(0);
    }

    private void loadFromBinaryFile() {
    }

    private void saveToBinaryFile() {

    }

    private void deleteBinaryFile() {
    }

    private void createProject() {
        final ProjectDTO project = getProject();
        projectService.insert(project);
        System.out.println("*** Project created: " + project + " ***");
    }

    private ProjectDTO getProject() {
        final String name = getStringFieldFromConsole("Project name");
        final String description = getStringFieldFromConsole("Project Description");
        final Date startDate = getDateFieldFromConsole("Start date");
        final Date endDate = getDateFieldFromConsole("End date");
        final ProjectDTO project = new ProjectDTO();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        return project;
    }

    private void showAllProjects() {
        final List<ProjectDTO> projects = projectService.findAll();
        projects.forEach(System.out::println);
    }

    private void showProjectInfo() {
        System.out.println("*** Please enter id ***");
        showAllProjects();
        final ProjectDTO project = projectService.findById(scanner.nextLine());
        if (project == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(project.info());
    }

    private void removeProject() {
        System.out.println("*** Please enter id ***");
        showAllProjects();
        if (!projectService.delete(scanner.nextLine())) System.out.println("*** Wrong Id ***");
    }

    private void removeAllProjects() {
        projectService.deleteAll();
    }

    private void createTask() {
        final TaskDTO task = getTask();
        taskService.insert(task);
        System.out.println("*** Task created: " + task + " ***");
    }

    private TaskDTO getTask() {
        final String name = getStringFieldFromConsole("Task name");
        final String description = getStringFieldFromConsole("Description");
        final Date startDate = getDateFieldFromConsole("Start date");
        final Date endDate = getDateFieldFromConsole("End date");
        final String projectId = getProjectIdFromConsole();
        final TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setProjectId(projectId);
        return task;
    }

    private void showAllTasks() {
        final List<TaskDTO> tasks = taskService.findAll();
        tasks.forEach(System.out::println);
    }

    private void showTaskInfo() {
        System.out.println("*** Please enter id ***");
        showAllTasks();
        final TaskDTO task = taskService.findById(scanner.nextLine());
        if (task == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(task.info());
    }

    private void showTaskInProject() {
        System.out.println("*** Please enter project id ***");
        showAllProjects();
        List<TaskDTO> tasks = taskService.findByProjectId(scanner.nextLine());
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found");
            return;
        }
        tasks.forEach(System.out::println);
    }

    private void removeTask() {
        System.out.println("*** Please enter id ***");
        showAllTasks();
        if (!taskService.delete(scanner.nextLine())) System.out.println("*** Wrong id ***");
    }

    private void removeAllTasks() {
        taskService.deleteAll();
    }

    private String getStringFieldFromConsole(final String field) {
        String name;
        boolean flag;
        do {
            System.out.println("*** Please write " + field + " ***");
            flag = true;
            name = scanner.nextLine();
            if (name == null || name.isEmpty()) {
                System.out.println("*** " + field + " can't be empty ***");
                flag = false;
            }
        } while (!flag);
        return name;
    }

    private Date getDateFieldFromConsole(final String field) {
        String deadline;
        boolean flag;
        do {
            flag = true;
            System.out.println("*** Please write " + field + " in format dd.mm.yyyy ***");
            deadline = scanner.nextLine();
            if (!deadline.matches(DateUtils.DATE_REGEX)) {
                System.out.println("*** Wrong format ***");
                flag = false;
            }
        } while (!flag);
        return getDateFromString(deadline);
    }

    @Nullable
    private Date getDateFromString(@Nullable final String deadline) {
        if (deadline == null || deadline.isEmpty()) return null;
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String getProjectIdFromConsole() {
        String id;
        boolean flag;
        do {
            System.out.println("*** Please write project id ***");
            showAllProjects();
            flag = true;
            id = scanner.nextLine();
            if (id == null || id.isEmpty() || !projectService.isExist(id)) {
                System.out.println("*** Wrong id ***");
                flag = false;
            }
        } while (!flag);
        return id;
    }

    private Commands parseLine(final String nextLine) {
        if (nextLine.startsWith(Commands.HELP.toString())) return Commands.HELP;
        if (nextLine.startsWith(Commands.EXIT.toString())) return Commands.EXIT;
        if (nextLine.startsWith(Commands.PROJECT_CREATE.toString())) return Commands.PROJECT_CREATE;
        if (nextLine.startsWith(Commands.PROJECT_LIST.toString())) return Commands.PROJECT_LIST;
        if (nextLine.startsWith(Commands.PROJECT_INFO.toString())) return Commands.PROJECT_INFO;
        if (nextLine.startsWith(Commands.PROJECT_REMOVE.toString())) return Commands.PROJECT_REMOVE;
        if (nextLine.startsWith(Commands.PROJECT_CLEAR.toString())) return Commands.PROJECT_CLEAR;
        if (nextLine.startsWith(Commands.TASK_CREATE.toString())) return Commands.TASK_CREATE;
        if (nextLine.startsWith(Commands.TASK_LIST.toString())) return Commands.TASK_LIST;
        if (nextLine.startsWith(Commands.TASK_INFO.toString())) return Commands.TASK_INFO;
        if (nextLine.startsWith(Commands.TASK_IN_PROJECT.toString())) return Commands.TASK_IN_PROJECT;
        if (nextLine.startsWith(Commands.TASK_REMOVE.toString())) return Commands.TASK_REMOVE;
        if (nextLine.startsWith(Commands.TASK_CLEAR.toString())) return Commands.TASK_CLEAR;
        return null;
    }
}
