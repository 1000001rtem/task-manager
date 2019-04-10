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

    public ProjectDTO(final Project project) {
        if (project == null) return;
        if (project.getId() != null && !project.getId().isEmpty()) this.id = project.getId();
        if (project.getName() != null && !project.getName().isEmpty()) this.name = project.getName();
        if (project.getDescription() != null && !project.getDescription().isEmpty()) {
            this.description = project.getDescription();
        }
        if (project.getStartDate() != null) this.startDate = project.getStartDate();
        if (project.getEndDate() != null) this.endDate = project.getEndDate();
    }

    public String info() {
        return name +
                "{id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public String toString() {
        return "{id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
