package ru.eremin.tm.server.model;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.config.DBConfig;
import ru.eremin.tm.server.config.EntityFactory;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.model.entity.User;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.service.ProjectService;
import ru.eremin.tm.server.service.TaskService;
import ru.eremin.tm.server.service.UserService;
import ru.eremin.tm.server.utils.PasswordHashUtil;

import javax.persistence.EntityManagerFactory;
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
        final EntityManagerFactory entityManagerFactory = DBConfig.getFactory();
        userService = new UserService(entityManagerFactory);
        taskService = new TaskService(entityManagerFactory);
        projectService = new ProjectService(entityManagerFactory);

        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLogin("testLogin");
        user.setHashPassword(PasswordHashUtil.md5("testPassword"));
        user.setRole(Role.ADMIN);

        project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setName("testProject");
        project.setDescription("twtdtghf");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setUser(user);

        task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setName("testTask");
        task.setDescription("testets");
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setProject(project);
        task.setUser(user);

        userDTO = EntityFactory.getUser();

        projectDTO = EntityFactory.getProject(userDTO);

        taskDTO = EntityFactory.getTask(projectDTO, userDTO);
    }

    @Test
    @Ignore
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
        assertEquals(projectDTOtmp.getUserId(), project.getUser());

        final TaskDTO taskDTOtmp = new TaskDTO(task);
        assertEquals(taskDTOtmp.getId(), task.getId());
        assertEquals(taskDTOtmp.getName(), task.getName());
        assertEquals(taskDTOtmp.getDescription(), task.getDescription());
        assertEquals(taskDTOtmp.getStartDate(), task.getStartDate());
        assertEquals(taskDTOtmp.getEndDate(), task.getEndDate());
        assertEquals(taskDTOtmp.getProjectId(), task.getProject());
        assertEquals(taskDTOtmp.getUserId(), task.getUser());
    }

    @Test
    @Ignore
    public void dtoToEntity() {
        final EntityManagerFactory entityManagerFactory = DBConfig.getFactory();
        final User user = userService.getEntity(userDTO, entityManagerFactory.createEntityManager());
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getLogin(), user.getLogin());
        assertEquals(userDTO.getHashPassword(), user.getHashPassword());
        assertEquals(userDTO.getRole(), user.getRole());

        final Project project = projectService.getEntity(projectDTO, entityManagerFactory.createEntityManager());
        assertEquals(projectDTO.getId(), project.getId());
        assertEquals(projectDTO.getName(), project.getName());
        assertEquals(projectDTO.getDescription(), project.getDescription());
        assertEquals(projectDTO.getStartDate(), project.getStartDate());
        assertEquals(projectDTO.getEndDate(), project.getEndDate());
        assertEquals(projectDTO.getUserId(), project.getUser());

        final Task task = taskService.getEntity(taskDTO, entityManagerFactory.createEntityManager());
        assertEquals(taskDTO.getId(), task.getId());
        assertEquals(taskDTO.getName(), task.getName());
        assertEquals(taskDTO.getDescription(), task.getDescription());
        assertEquals(taskDTO.getStartDate(), task.getStartDate());
        assertEquals(taskDTO.getEndDate(), task.getEndDate());
        assertEquals(taskDTO.getProjectId(), task.getProject());
        assertEquals(taskDTO.getUserId(), task.getUser());
    }

}
