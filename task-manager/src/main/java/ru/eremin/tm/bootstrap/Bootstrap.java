package ru.eremin.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.repository.ProjectRepository;
import ru.eremin.tm.model.repository.TaskRepository;
import ru.eremin.tm.utils.Commands;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.service.ProjectService;
import ru.eremin.tm.model.service.TaskService;
import ru.eremin.tm.utils.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public class Bootstrap {

    @NotNull
    private final ProjectService projectService;

    @NotNull
    private final TaskService taskService;

    @NotNull
    private final Scanner scanner;

    public Bootstrap() {
        this.scanner = new Scanner(System.in);
        @NotNull final ProjectRepository projectRepository = new ProjectRepository();
        @NotNull final TaskRepository taskRepository = new TaskRepository();
        this.taskService = new TaskService(taskRepository);
        this.projectService = new ProjectService(projectRepository, this.taskService);
    }

    public void init() {
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
        @NotNull final ProjectDTO project = getProject();
        projectService.persist(project);
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

    private void showAllProjects() {
        @NotNull final List<ProjectDTO> projects = projectService.findAll();
        projects.forEach(System.out::println);
    }

    private void showProjectInfo() {
        System.out.println("*** Please enter id ***");
        showAllProjects();
        @NotNull final ProjectDTO project = projectService.findOne(scanner.nextLine());
        if (project == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(project.info());
    }

    private void removeProject() {
        System.out.println("*** Please enter id ***");
        showAllProjects();
        if (!projectService.remove(scanner.nextLine())) System.out.println("*** Wrong Id ***");
    }

    private void removeAllProjects() {
        projectService.removeAll();
    }

    private void createTask() {
        @NotNull final TaskDTO task = getTask();
        taskService.persist(task);
        System.out.println("*** Task created: " + task + " ***");
    }

    @NotNull
    private TaskDTO getTask() {
        @NotNull final String name = getStringFieldFromConsole("Task name");
        @NotNull final String description = getStringFieldFromConsole("Description");
        @NotNull final Date startDate = getDateFieldFromConsole("Start date");
        @NotNull final Date endDate = getDateFieldFromConsole("End date");
        @NotNull final String projectId = getProjectIdFromConsole();
        @NotNull final TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (endDate != null) task.setEndDate(endDate);
        task.setProjectId(projectId);
        return task;
    }

    private void showAllTasks() {
        @NotNull final List<TaskDTO> tasks = taskService.findAll();
        tasks.forEach(System.out::println);
    }

    private void showTaskInfo() {
        System.out.println("*** Please enter id ***");
        showAllTasks();
        @Nullable final TaskDTO task = taskService.findOne(scanner.nextLine());
        if (task == null) {
            System.out.println("*** Wrong Id ***");
            return;
        }
        System.out.println(task.info());
    }

    private void showTaskInProject() {
        System.out.println("*** Please enter project id ***");
        showAllProjects();
        @NotNull final List<TaskDTO> tasks = taskService.findByProjectId(scanner.nextLine());
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found");
            return;
        }
        tasks.forEach(System.out::println);
    }

    private void removeTask() {
        System.out.println("*** Please enter id ***");
        showAllTasks();
        if (!taskService.remove(scanner.nextLine())) System.out.println("*** Wrong id ***");
    }

    private void removeAllTasks() {
        taskService.removeAll();
    }

    @NotNull
    private String getStringFieldFromConsole(@Nullable final String field) {
        if (field == null || field.isEmpty()) throw new NullPointerException("wrong method attribute");
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

    @Nullable
    private Date getDateFieldFromConsole(@Nullable final String field) {
        if (field == null || field.isEmpty()) throw new NullPointerException("wrong method attribute");
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
    private Date getDateFromString(@NotNull final String dateString) {
        @NotNull final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @NotNull
    private String getProjectIdFromConsole() {
        String id;
        boolean flag;
        do {
            System.out.println("*** Please write project id ***");
            showAllProjects();
            flag = true;
            id = scanner.nextLine();
            if (id == null || id.isEmpty() || projectService.isExist(id)) {
                System.out.println("*** Wrong id ***");
                flag = false;
            }
        } while (!flag);
        return id;
    }

    @Nullable
    private Commands parseLine(@Nullable final String nextLine) {
        if (nextLine == null || nextLine.isEmpty()) return null;
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
