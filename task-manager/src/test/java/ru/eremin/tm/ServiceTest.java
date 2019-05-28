package ru.eremin.tm;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.api.DataTest;
import ru.eremin.tm.api.service.IProjectService;
import ru.eremin.tm.api.service.ITaskService;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.config.EntityFactory;
import ru.eremin.tm.config.TestConfiguration;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    @Test
    @Category(DataTest.class)
    public void test() throws IncorrectDataException, AccessForbiddenException {
        final UserDTO user = EntityFactory.getUser();
        final ProjectDTO project = EntityFactory.getProject(user);
        final TaskDTO task = EntityFactory.getTask(project, user);

        final int beforeUsersSize = userService.findAll().size();
        final int beforeProjectsSize = projectService.findByUserId(user.getId()).size();
        final int beforeTasksSize = taskService.findByUserId(user.getId()).size();


        userService.persist(user);
        projectService.persist(project);
        taskService.persist(task);

        assertNotNull(userService.findOne(user.getId()));
        assertNotNull(projectService.findOne(project.getId()));
        assertNotNull(taskService.findOne(task.getId()));

        final String update = "update";

        user.setLogin(update);
        project.setName(update);
        task.setName(update);

        userService.update(user);
        projectService.update(project);
        taskService.update(task);

        assertEquals(update, userService.findOne(user.getId()).getLogin());
        assertEquals(update, projectService.findOne(project.getId()).getName());
        assertEquals(update, taskService.findOne(task.getId()).getName());

        taskService.remove(task.getId());
        projectService.remove(project.getId());
        userService.remove(user.getId());

        assertEquals(beforeUsersSize, userService.findAll().size());
        assertEquals(beforeProjectsSize, projectService.findByUserId(user.getId()).size());
        assertEquals(beforeTasksSize, taskService.findByUserId(user.getId()).size());
    }

    @Test
    @Category(DataTest.class)
    public void userCascadeTest() throws IncorrectDataException, AccessForbiddenException {
        final UserDTO user = EntityFactory.getUser();
        final ProjectDTO project = EntityFactory.getProject(user);
        final TaskDTO task = EntityFactory.getTask(project, user);

        final int beforeUsersSize = userService.findAll().size();
        final int beforeProjectsSize = projectService.findByUserId(user.getId()).size();
        final int beforeTasksSize = taskService.findByUserId(user.getId()).size();

        userService.persist(user);
        projectService.persist(project);
        taskService.persist(task);

        assertNotNull(userService.findOne(user.getId()));
        assertNotNull(projectService.findOne(project.getId()));
        assertNotNull(taskService.findOne(task.getId()));

        userService.remove(user.getId());

        assertEquals(beforeUsersSize, userService.findAll().size());
        assertEquals(beforeProjectsSize, projectService.findByUserId(user.getId()).size());
        assertEquals(beforeTasksSize, taskService.findByUserId(user.getId()).size());
    }

    @Test
    @Category(DataTest.class)
    public void projectCascadeTest() throws IncorrectDataException, AccessForbiddenException {
        final UserDTO user = EntityFactory.getUser();
        final ProjectDTO project = EntityFactory.getProject(user);
        final TaskDTO task = EntityFactory.getTask(project, user);

        final int beforeProjectsSize = projectService.findByUserId(user.getId()).size();
        final int beforeTasksSize = taskService.findByUserId(user.getId()).size();

        userService.persist(user);
        projectService.persist(project);
        taskService.persist(task);

        assertNotNull(userService.findOne(user.getId()));
        assertNotNull(projectService.findOne(project.getId()));
        assertNotNull(taskService.findOne(task.getId()));

        projectService.remove(project.getId());

        assertEquals(beforeProjectsSize, projectService.findByUserId(user.getId()).size());
        assertEquals(beforeTasksSize, taskService.findByUserId(user.getId()).size());
    }

}
