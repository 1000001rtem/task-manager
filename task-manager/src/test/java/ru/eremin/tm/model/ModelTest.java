package ru.eremin.tm.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.eremin.tm.config.Order;
import ru.eremin.tm.config.OrderedRunner;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.Role;
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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@RunWith(OrderedRunner.class)
public class ModelTest {

    private static ProjectDTO projectDTO;
    private static TaskDTO taskDTO;
    private static UserDTO userDTO;

    private static IProjectService projectService;
    private static ITaskService taskService;
    private static IUserService userService;

    @BeforeClass
    public static void before() {
        userDTO = new UserDTO();
        userDTO.setLogin("testLogin");
        userDTO.setHashPassword(Utils.getHash("testPassword"));
        userDTO.setRole(Role.USER);

        projectDTO = new ProjectDTO();
        projectDTO.setName("testProject");
        projectDTO.setDescription("testProjectDescription");
        projectDTO.setStartDate(new Date());
        projectDTO.setEndDate(new Date());
        projectDTO.setUserId(userDTO.getId());

        taskDTO = new TaskDTO();
        taskDTO.setName("testTask");
        taskDTO.setDescription("testTaskDescription");
        taskDTO.setStartDate(new Date());
        taskDTO.setEndDate(new Date());
        taskDTO.setProjectId(projectDTO.getId());
        taskDTO.setUserId(userDTO.getId());

        final IProjectRepository projectRepository = new ProjectRepository();
        final ITaskRepository taskRepository = new TaskRepository();
        final IUserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
        taskService = new TaskService(taskRepository);
        projectService = new ProjectService(projectRepository, taskService);
    }

    @Test
    @Order(order = 1)
    public void insertTest() {
        final int beforeUsersSize = userService.findAll().size();
        final int beforeProjectsSize = projectService.findAll().size();
        final int beforeTasksSize = taskService.findAll().size();

        userService.persist(userDTO);
        projectService.persist(projectDTO);
        taskService.persist(taskDTO);

        assertEquals(beforeUsersSize + 1, userService.findAll().size());
        assertEquals(beforeProjectsSize + 1, projectService.findAll().size());
        assertEquals(beforeTasksSize + 1, taskService.findAll().size());
    }

    @Test
    @Order(order = 2)
    public void findTest() {
        assertEquals(userDTO.getId(), userService.findOne(userDTO.getId()).getId());
        assertEquals(userDTO.getId(), userService.findByLogin(userDTO.getLogin()).getId());
        assertEquals(projectDTO.getId(), projectService.findOne(projectDTO.getId()).getId());
        assertNotNull(projectService.findByUserId(userDTO.getId()));
        assertEquals(taskDTO.getId(), taskService.findOne(taskDTO.getId()).getId());
        assertNotNull(taskService.findByProjectId(projectDTO.getId()));
        assertNotNull(taskService.findByUserId(userDTO.getId()));
    }

    @Test
    @Order(order = 3)
    public void updateTest() {
        final UserDTO userTMP = userService.findOne(userDTO.getId());
        final ProjectDTO projectTMP = projectService.findOne(projectDTO.getId());
        final TaskDTO taskTMP = taskService.findOne(taskDTO.getId());

        assertNotNull(userTMP);
        assertNotNull(projectTMP);
        assertNotNull(taskTMP);

        final String updateName = "UpdateName";

        userTMP.setLogin(updateName);
        taskTMP.setName(updateName);
        projectTMP.setName(updateName);

        userService.update(userTMP);
        projectService.update(projectTMP);
        taskService.update(taskTMP);

        assertEquals(updateName, userService.findOne(userDTO.getId()).getLogin());
        assertEquals(updateName, projectService.findOne(projectDTO.getId()).getName());
        assertEquals(updateName, taskService.findOne(taskDTO.getId()).getName());
    }

    @Test
    @Order(order = 4)
    public void mergeTest() {
        final UserDTO userDTO1 = new UserDTO();
        userDTO1.setLogin("testUser");
        userDTO1.setHashPassword(Utils.getHash("testPassword"));
        userDTO1.setRole(Role.USER);

        final ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setName("testProject");
        projectDTO1.setDescription("testProjectDescription");
        projectDTO1.setStartDate(new Date());
        projectDTO1.setEndDate(new Date());

        final TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setName("testTask");
        taskDTO1.setDescription("testTaskDescription");
        taskDTO1.setStartDate(new Date());
        taskDTO1.setEndDate(new Date());
        taskDTO1.setProjectId(projectDTO1.getId());

        userService.merge(userDTO1);
        projectService.merge(projectDTO1);
        taskService.merge(taskDTO1);

        assertNotNull(userService.findOne(userDTO1.getId()));
        assertNotNull(projectService.findOne(projectDTO1.getId()));
        assertNotNull(taskService.findOne(taskDTO1.getId()));

        userDTO1.setLogin("UpdateLogin");
        projectDTO1.setName("UpdateName");
        taskDTO1.setName("UpdateName");

        userService.merge(userDTO1);
        projectService.merge(projectDTO1);
        taskService.merge(taskDTO1);

        assertEquals("UpdateLogin", userService.findOne(userDTO1.getId()).getLogin());
        assertEquals("UpdateName", projectService.findOne(projectDTO1.getId()).getName());
        assertEquals("UpdateName", taskService.findOne(taskDTO1.getId()).getName());

    }

    @Test
    @Order(order = 5)
    public void deleteTest() {
        final int beforeUsersSize = userService.findAll().size();
        final int beforeProjectsSize = projectService.findAll().size();
        final int beforeTasksSize = taskService.findAll().size();

        userService.remove(userDTO.getId());
        projectService.remove(projectDTO.getId());
        taskService.remove(taskDTO.getId());

        assertEquals(beforeUsersSize - 1, userService.findAll().size());
        assertEquals(beforeProjectsSize - 1, projectService.findAll().size());
        assertEquals(beforeTasksSize - 1, taskService.findAll().size());
    }

}
