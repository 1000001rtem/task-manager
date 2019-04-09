package ru.eremin.tm;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.Commands;
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
        do {
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
                case TASK_REMOVE:
                    removeTask();
                    break;
                case TASK_CLEAR:
                    removeAllTasks();
                    break;
            }
        } while (!answer.equals(Commands.EXIT));
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
        final String name = getNameFromConsole();
        final Date deadline = getDeadLineFromConsole();
        final ProjectDTO project = new ProjectDTO();
        project.setName(name);
        project.setDeadline(deadline);
        return project;
    }

    private void showAllProjects() {
        final List<ProjectDTO> projects = projectService.findAll();
        projects.forEach(System.out::println);
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
        final String name = getNameFromConsole();
        final Date deadline = getDeadLineFromConsole();
        final String projectId = getProjectIdFromConsole();
        final TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setDeadline(deadline);
        task.setProjectId(projectId);
        return task;
    }

    private void showAllTasks() {
        final List<TaskDTO> tasks = taskService.findAll();
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

    private String getNameFromConsole() {
        String name;
        boolean flag;
        do {
            System.out.println("*** Please write name ***");
            flag = true;
            name = scanner.nextLine();
            if (name == null || name.isEmpty()) {
                System.out.println("*** Name can't be empty ***");
                flag = false;
            }
        } while (!flag);
        return name;
    }

    private Date getDeadLineFromConsole() {
        String deadline;
        boolean flag;
        do {
            flag = true;
            System.out.println("*** Please write deadline date in format dd.mm.yyyy ***");
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
        if (nextLine.startsWith(Commands.PROJECT_REMOVE.toString())) return Commands.PROJECT_REMOVE;
        if (nextLine.startsWith(Commands.PROJECT_CLEAR.toString())) return Commands.PROJECT_CLEAR;
        if (nextLine.startsWith(Commands.TASK_CREATE.toString())) return Commands.TASK_CREATE;
        if (nextLine.startsWith(Commands.TASK_LIST.toString())) return Commands.TASK_LIST;
        if (nextLine.startsWith(Commands.TASK_REMOVE.toString())) return Commands.TASK_REMOVE;
        if (nextLine.startsWith(Commands.TASK_CLEAR.toString())) return Commands.TASK_CLEAR;
        return null;
    }
}
