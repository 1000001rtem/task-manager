package ru.eremin.tm.model;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.entity.Role;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.model.entity.User;
import ru.eremin.tm.model.repository.ProjectRepository;
import ru.eremin.tm.model.repository.TaskRepository;
import ru.eremin.tm.model.repository.UserRepository;
import ru.eremin.tm.model.repository.api.IProjectRepository;
import ru.eremin.tm.model.repository.api.ITaskRepository;
import ru.eremin.tm.model.repository.api.IUserRepository;
import ru.eremin.tm.model.service.ProjectService;
import ru.eremin.tm.model.service.TaskService;
import ru.eremin.tm.model.service.UserService;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;
import ru.eremin.tm.model.service.api.IUserService;
import ru.eremin.tm.utils.Utils;

import java.util.Date;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class MappingEntityTest {

    private static Task task;
    private static Project project;
    private static User user;

    private static TaskDTO taskDTO;
    private static ProjectDTO projectDTO;
    private static UserDTO userDTO;

    private static IUserService userService;
    private static IProjectService projectService;
    private static ITaskService taskService;

    @BeforeClass
    public static void before() {
        final IUserRepository userRepository = new UserRepository();
        final IProjectRepository projectRepository = new ProjectRepository();
        final ITaskRepository taskRepository = new TaskRepository();
        userService = new UserService(userRepository);
        taskService = new TaskService(taskRepository);
        projectService = new ProjectService(projectRepository, taskService);

        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLogin("testLogin");
        user.setHashPassword(Utils.getHash("testPassword"));
        user.setRole(Role.ADMIN);

        project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setName("testProject");
        project.setDescription("twtdtghf");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setUserId(user.getId());

        task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setName("testTask");
        task.setDescription("testets");
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setProjectId(project.getId());
        task.setUserId(user.getId());

        userDTO = new UserDTO();
        userDTO.setLogin("testLoginDTO");
        userDTO.setHashPassword(Utils.getHash("testPasswordDTO"));
        userDTO.setRole(Role.USER);

        projectDTO = new ProjectDTO();
        projectDTO.setName("testProjectDTO");
        projectDTO.setDescription("bcvjfkjdg");
        projectDTO.setStartDate(new Date());
        projectDTO.setEndDate(new Date());
        projectDTO.setUserId(userDTO.getId());

        taskDTO = new TaskDTO();
        taskDTO.setName("testTaskDTO");
        taskDTO.setDescription("fdhfgdfig");
        taskDTO.setStartDate(new Date());
        taskDTO.setEndDate(new Date());
        taskDTO.setProjectId(projectDTO.getId());
        taskDTO.setUserId(userDTO.getId());
    }

    @Test
    public void entityToDTO() {
        final UserDTO userDTOtmp = new UserDTO(user);
        assertEquals(userDTOtmp.getId(), user.getId());
        assertEquals(userDTOtmp.getLogin(), user.getLogin());
        assertEquals(userDTOtmp.getHashPassword(), user.getHashPassword());
        assertEquals(userDTOtmp.getRole(), user.getRole());

        final ProjectDTO projectDTOtmp = new ProjectDTO(project);
        assertEquals(projectDTOtmp.getId(), project.getId());
        assertEquals(projectDTOtmp.getName(), project.getName());
        assertEquals(projectDTOtmp.getDescription(), project.getDescription());
        assertEquals(projectDTOtmp.getStartDate(), project.getStartDate());
        assertEquals(projectDTOtmp.getEndDate(), project.getEndDate());
        assertEquals(projectDTOtmp.getUserId(), project.getUserId());

        final TaskDTO taskDTOtmp = new TaskDTO(task);
        assertEquals(taskDTOtmp.getId(), task.getId());
        assertEquals(taskDTOtmp.getName(), task.getName());
        assertEquals(taskDTOtmp.getDescription(), task.getDescription());
        assertEquals(taskDTOtmp.getStartDate(), task.getStartDate());
        assertEquals(taskDTOtmp.getEndDate(), task.getEndDate());
        assertEquals(taskDTOtmp.getProjectId(), task.getProjectId());
        assertEquals(taskDTOtmp.getUserId(), task.getUserId());
    }

    @Test
    public void dtoToEntity() {
        final User user = userService.getEntity(userDTO);
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getLogin(), user.getLogin());
        assertEquals(userDTO.getHashPassword(), user.getHashPassword());
        assertEquals(userDTO.getRole(), user.getRole());

        final Project project = projectService.getEntity(projectDTO);
        assertEquals(projectDTO.getId(), project.getId());
        assertEquals(projectDTO.getName(), project.getName());
        assertEquals(projectDTO.getDescription(), project.getDescription());
        assertEquals(projectDTO.getStartDate(), project.getStartDate());
        assertEquals(projectDTO.getEndDate(), project.getEndDate());
        assertEquals(projectDTO.getUserId(), project.getUserId());

        final Task task = taskService.getEntity(taskDTO);
        assertEquals(taskDTO.getId(), task.getId());
        assertEquals(taskDTO.getName(), task.getName());
        assertEquals(taskDTO.getDescription(), task.getDescription());
        assertEquals(taskDTO.getStartDate(), task.getStartDate());
        assertEquals(taskDTO.getEndDate(), task.getEndDate());
        assertEquals(taskDTO.getProjectId(), task.getProjectId());
        assertEquals(taskDTO.getUserId(), task.getUserId());
    }

}
