package ru.eremin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eremin.tm.api.IProjectService;
import ru.eremin.tm.model.dto.ProjectDTO;

import javax.inject.Named;
import java.util.List;

/**
 * @autor Eremin Artem on 21.05.2019.
 */

@Named
@Scope("session")
@Component("second")
@URLMapping(id = "second-home", pattern = "/secondHome", viewId = "/WEB-INF/views/second-home.xhtml")
public class SecondController {
    @Autowired
    private IProjectService projectService;

    public List<ProjectDTO> getProjects(){
        return projectService.findAll();
    }

    public String change(){
        System.out.println("FFFFFFFFFFF");
        return "second-view";
    }
}
