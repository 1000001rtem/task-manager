package ru.eremin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eremin.tm.model.entity.Project;

import java.io.Serializable;
import java.util.Date;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = -379365728203711699L;

    private String name;

    private Date deadline;

    public ProjectDTO(final Project project) {
        if (project == null) return;
        if (project.getId() != null && !project.getId().isEmpty()) this.id = project.getId();
        if (project.getName() != null && !project.getName().isEmpty()) this.name = project.getName();
        if (project.getDeadline() != null) this.deadline = project.getDeadline();
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                '}';
    }

}
