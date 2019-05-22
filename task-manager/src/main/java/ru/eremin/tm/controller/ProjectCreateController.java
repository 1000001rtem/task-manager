package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 * @autor Eremin Artem on 22.05.2019.
 */

@Getter
@Setter
@ManagedBean(name = "projectCreate")
@URLMapping(
        id = "projectCreate",
        pattern = "/projectCreate",
        viewId = "/project-create-view.xhtml"
)
public class ProjectCreateController {

    @ManagedProperty(value = "#{projectService}")
    private IProjectService projectService;

    private ProjectDTO projectDTO = new ProjectDTO();

    public void createProject() throws IncorrectDataException {
        projectDTO.setUserId("6706e691-2f78-45ad-b021-3730c48959f0");
        projectService.persist(projectDTO);
    }

}
