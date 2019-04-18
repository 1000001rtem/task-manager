package ru.eremin.tm.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * @autor av.eremin on 17.04.2019.
 */
@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Domain implements Serializable {

    private static final long serialVersionUID = 7169679933097471756L;

    @Nullable
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    private List<ProjectDTO> projects;

    @Nullable
    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks")
    private List<TaskDTO> tasks;

    public Domain(@Nullable final List<ProjectDTO> projects, @Nullable final List<TaskDTO> tasks) {
        if (projects == null || tasks == null) return;
        this.projects = projects;
        this.tasks = tasks;
    }

}
