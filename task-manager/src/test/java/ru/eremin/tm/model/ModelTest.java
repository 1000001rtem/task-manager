package ru.eremin.tm.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.eremin.tm.config.Order;
import ru.eremin.tm.config.OrderedRunner;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.repository.ProjectRepository;
import ru.eremin.tm.model.repository.TaskRepository;
import ru.eremin.tm.model.service.ProjectService;
import ru.eremin.tm.model.service.TaskService;

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

    private static ProjectService projectService;
    private static TaskService taskService;

    @BeforeClass
    public static void before() {
        projectDTO = new ProjectDTO();
        projectDTO.setName("testProject");
        projectDTO.setDescription("testProjectDescription");
        projectDTO.setStartDate(new Date());
        projectDTO.setEndDate(new Date());

        taskDTO = new TaskDTO();
        taskDTO.setName("testTask");
        taskDTO.setDescription("testTaskDescription");
        taskDTO.setStartDate(new Date());
        taskDTO.setEndDate(new Date());
        taskDTO.setProjectId(projectDTO.getId());

        final ProjectRepository projectRepository = new ProjectRepository();
        final TaskRepository taskRepository = new TaskRepository();
        taskService = new TaskService(taskRepository);
        projectService = new ProjectService(projectRepository, taskService);
    }

    @Test
    @Order(order = 1)
    public void insertTest() {
        final int beforeProjectsSize = projectService.findAll().size();
        final int beforeTasksSize = taskService.findAll().size();

        projectService.persist(projectDTO);
        taskService.persist(taskDTO);

        assertEquals(beforeProjectsSize + 1, projectService.findAll().size());
        assertEquals(beforeTasksSize + 1, taskService.findAll().size());
    }

    @Test
    @Order(order = 2)
    public void findTest() {
        assertNotNull(projectService.findOne(projectDTO.getId()));
        assertNotNull(taskService.findOne(taskDTO.getId()));
        assertNotNull(taskService.findByProjectId(projectDTO.getId()));
    }

    @Test
    @Order(order = 3)
    public void updateTest() {
        final TaskDTO taskTMP = taskService.findOne(taskDTO.getId());
        final ProjectDTO projectTMP = projectService.findOne(projectDTO.getId());

        assertNotNull(taskTMP);
        assertNotNull(projectTMP);

        final String updateName = "UpdateName";

        taskTMP.setName(updateName);
        projectTMP.setName(updateName);

        taskService.update(taskTMP);
        projectService.update(projectTMP);

        assertEquals(updateName, projectService.findOne(projectDTO.getId()).getName());
        assertEquals(updateName, taskService.findOne(taskDTO.getId()).getName());
    }

    @Test
    @Order(order = 4)
    public void mergeTest() {
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

        projectService.merge(projectDTO1);
        taskService.merge(taskDTO1);

        assertNotNull(projectService.findOne(projectDTO1.getId()));
        assertNotNull(taskService.findOne(taskDTO1.getId()));

        projectDTO1.setName("UpdateName");
        taskDTO1.setName("UpdateName");

        projectService.merge(projectDTO1);
        taskService.merge(taskDTO1);

        assertEquals("UpdateName", projectService.findOne(projectDTO1.getId()).getName());
        assertEquals("UpdateName", taskService.findOne(taskDTO1.getId()).getName());

    }

    @Test
    @Order(order = 5)
    public void deleteTest() {
        final int beforeProjectsSize = projectService.findAll().size();
        final int beforeTasksSize = taskService.findAll().size();

        taskService.remove(taskDTO.getId());
        projectService.remove(projectDTO.getId());

        assertEquals(beforeProjectsSize - 1, projectService.findAll().size());
        assertEquals(beforeTasksSize - 1, taskService.findAll().size());
    }

}
