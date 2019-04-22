package ru.eremin.tm.server.config;

import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;
import ru.eremin.tm.server.model.entity.session.Session;
import ru.eremin.tm.server.utils.PasswordHashUtil;
import ru.eremin.tm.server.utils.SignatureUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @autor av.eremin on 15.04.2019.
 */

public class EntityFactory {

    public static ProjectDTO getProject(final UserDTO userDTO) {
        final ProjectDTO project = new ProjectDTO();
        project.setId(UUID.randomUUID().toString());
        project.setName("testProject" + getRandom());
        project.setDescription("twtdtghf" + getRandom());
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setUserId(userDTO.getId());
        return project;
    }

    public static TaskDTO getTask(final ProjectDTO projectDTO, final UserDTO userDTO) {
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("testTask" + getRandom());
        taskDTO.setDescription("testTaskDescription" + getRandom());
        taskDTO.setStartDate(new Date());
        taskDTO.setEndDate(new Date());
        taskDTO.setProjectId(projectDTO.getId());
        taskDTO.setUserId(userDTO.getId());
        return taskDTO;
    }

    public static UserDTO getUser() {
        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testLoginDTO" + getRandom());
        userDTO.setHashPassword(PasswordHashUtil.md5("testPasswordDTO"));
        userDTO.setRole(Role.USER);
        return userDTO;
    }

    public static SessionDTO getSession(final UserDTO userDTO){
        SessionDTO sessionDTO = new SessionDTO(userDTO);
        sessionDTO.setSign(SignatureUtil.sign(sessionDTO, "54mjg5&4", 10));
        return sessionDTO;
    }

    private static int getRandom() {
        return Math.round(30);
    }
}
