package ru.eremin.tm;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.eremin.tm.config.Order;
import ru.eremin.tm.config.OrderedRunner;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
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
        projectDTO.setDeadline(new Date());

        taskDTO = new TaskDTO();
        taskDTO.setName("testTask");
        taskDTO.setDeadline(new Date());
        taskDTO.setProjectId(projectDTO.getId());

        projectService = ProjectService.INSTANCE;
        taskService = TaskService.INSTANCE;
    }

    @Test
    @Order(order = 1)
    public void insertTest() {
        final int beforeProjectsSize = projectService.findAll().size();
        final int beforeTasksSize = taskService.findAll().size();

        projectService.insert(projectDTO);
        taskService.insert(taskDTO);

        assertEquals(beforeProjectsSize + 1, projectService.findAll().size());
        assertEquals(beforeTasksSize + 1, taskService.findAll().size());
    }

    @Test
    @Order(order = 2)
    public void findTest(){
        assertNotNull(projectService.findById(projectDTO.getId()));
        assertNotNull(taskService.findById(taskDTO.getId()));
        assertNotNull(taskService.findByProjectId(projectDTO.getId()));
    }

    @Test
    @Order(order = 3)
    public void updateTest(){
        final TaskDTO taskTMP = taskService.findById(taskDTO.getId());
        final ProjectDTO projectTMP = projectService.findById(projectDTO.getId());

        assertNotNull(taskTMP);
        assertNotNull(projectTMP);

        final String updateName = "UpdateName";

        taskTMP.setName(updateName);
        projectTMP.setName(updateName);

        taskService.update(taskTMP);
        projectService.update(projectTMP);

        assertEquals(updateName, projectService.findById(projectDTO.getId()).getName());
        assertEquals(updateName, taskService.findById(taskDTO.getId()).getName());
    }

    @Test
    @Order(order = 4)
    public void deleteTest(){
        final int beforeProjectsSize = projectService.findAll().size();
        final int beforeTasksSize = taskService.findAll().size();

        taskService.delete(taskDTO.getId());
        projectService.delete(projectDTO.getId());

        assertEquals(beforeProjectsSize - 1, projectService.findAll().size());
        assertEquals(beforeTasksSize - 1, taskService.findAll().size());
    }

}
